package domain.system.controller;

import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.PageQueryResult;
import domain.system.entity.RoleEntity;
import domain.system.service.RoleManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static domain.system.SystemWebForward.ROLEMANAGEMENTPANEL;
import static domain.system.SystemWebURLMapping.*;

@Controller
public class RoleManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleManagementController.class);

    final private RoleManagementService roleManagementService;

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
}
