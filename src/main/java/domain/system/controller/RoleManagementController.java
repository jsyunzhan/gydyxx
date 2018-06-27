package domain.system.controller;

import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.PageQueryResult;
import domain.shiro.entity.ResourceEntity;
import domain.system.entity.AuthorizationVOEntity;
import domain.system.entity.RoleEntity;
import domain.system.service.RoleManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static domain.shiro.entity.SystemConfig.ZERO;
import static domain.system.SystemWebForward.ROLEMANAGEMENTPANEL;
import static domain.system.SystemWebURLMapping.*;

@Controller
public class RoleManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleManagementController.class);

    final private RoleManagementService roleManagementService;
    private static final String RESOURCE_NAME = "resourceName";
    private static final String PARENT_ID = "parentId";

    @Autowired
    public RoleManagementController(RoleManagementService roleManagementService){
        this.roleManagementService = roleManagementService;
    }

    /**
     * 去角色管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = ROLE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(ROLEMANAGEMENTPANEL);
    }

    /**
     * 角色管理分页
     * @param roleEntity 查询实体 roleEntity
     * @return PageQueryResult
     */
    @RequestMapping(value = ROLE_LIST_GET)
    @ResponseBody
    public PageQueryResult roleListInfo(RoleEntity roleEntity ){

        return roleManagementService.roleListInfo(roleEntity);
    }


    /*
    验证roleName是否重名
     */
    @RequestMapping(value = ROLE_CHECK_ROLE_NAME)
    @ResponseBody
    public Boolean checkRoleName(@RequestParam("roleName") String roleName,
                                 @RequestParam(value = "id",required = false) Long id){
        return roleManagementService.checkRoleName(id,roleName);
    }

    /**
     * 新增
     */
    @RequestMapping(value = ROLE_ADD_POST)
    @ResponseBody
    public JsonResponseVO addRole(@RequestBody RoleEntity roleEntity){



        roleEntity.setCreateUserId(getLoginId());
        JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {

            final Boolean flag = roleManagementService.addRole(roleEntity);
            jsonResponseVO.setSuccess(flag);
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("角色名新增,roleName:{}",roleEntity.getRoleName());
            }
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /*
    修改
     */
    @RequestMapping(value = ROLE_EDIT_POST)
    @ResponseBody
    public JsonResponseVO editRole(@RequestBody RoleEntity roleEntity){
        roleEntity.setUpdateUserId(getLoginId());
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {

            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("角色名修改,roleName:{}",roleEntity.getRoleName());
            }
            final Boolean flag = roleManagementService.editRole(roleEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 删除
     */
    @RequestMapping(value = ROLE_DELETE_GET)
    @ResponseBody
    public JsonResponseVO deleRole(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("角色名删除,id:{}",id);
            }
            final Boolean flag = roleManagementService.deleRole(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 树
     * @return List
     */
    @RequestMapping(value = RESOURCES_LIST_GET)
    @ResponseBody
    public List resourceList(){

        final List<ResourceEntity> list = roleManagementService.resourceList();

        List treeList = null;
        try {
            treeList = createTreeGridTree(list, ZERO, ResourceEntity.class,
                    RESOURCE_NAME, PARENT_ID);
        } catch (ClassNotFoundException e) {
            LOGGER.error("构建树反射未找到类异常:", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("构建树反射安全权限异常:", e);
        } catch (NoSuchFieldException e) {
            LOGGER.error("构建树反射未找到文件异常:", e);
        }
        return treeList;
    }

    /**
     * 授权
     * @param authorizationVOEntity 授权实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESOURCES_AUTHORIZATION)
    @ResponseBody
    public JsonResponseVO authorization(@RequestBody AuthorizationVOEntity authorizationVOEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("角色授权,roleId:{}",authorizationVOEntity.getRoleId());
            }
            final Boolean flag = roleManagementService.authorization(authorizationVOEntity,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
