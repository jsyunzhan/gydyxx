package domain.home.controller;

import domain.home.entity.ResourcesEntity;
import domain.home.service.ResourcesManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_RESOURCES_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 家校资源
 */
@Controller
public class ResourcesManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesManagementController.class);

    final private ResourcesManagementService resourcesManagementService;

    @Autowired
    public ResourcesManagementController(ResourcesManagementService resourcesManagementService){
        this.resourcesManagementService = resourcesManagementService;
    }

    /**
     * 去家校资源页面
     * @return ModelAndView
     */
    @RequestMapping(value = RESOURCES_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_RESOURCES_PAGE);
    }

    /**
     * 家校资源分页
     * @param resourcesEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = RESOURCES_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult resourcesList(ResourcesEntity resourcesEntity){
        return resourcesManagementService.resourcesList(resourcesEntity);
    }

    /**
     * 家校资源新增
     * @param resourcesEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESOURCES_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO resourcesAdd(@RequestBody ResourcesEntity resourcesEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("家校资源新增,title:{}",resourcesEntity.getResourcesTitle());
            }
            resourcesEntity.setCreateUserId(getLoginId());
            final Boolean flag = resourcesManagementService.resourcesAdd(resourcesEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 家校资源修改
     * @param resourcesEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESOURCES_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO resourcesEdit(@RequestBody ResourcesEntity resourcesEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("家校资源修改,title:{}",resourcesEntity.getResourcesTitle());
            }
            resourcesEntity.setUpdateUserId(getLoginId());
            final Boolean flag = resourcesManagementService.resourcesEdit(resourcesEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 家校资源删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESOURCES_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO resourcesDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("家校资源删除,id:{}",id);
            }
            final Boolean flag = resourcesManagementService.resourcesDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
