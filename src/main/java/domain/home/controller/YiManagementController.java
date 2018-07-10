package domain.home.controller;

import domain.home.entity.YiEntity;
import domain.home.service.YiManagementService;
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

import static domain.home.HomeWebForward.TO_YI_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 致用邑
 */
@Controller
public class YiManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(YiManagementController.class);

    final private YiManagementService yiManagementService;

    @Autowired
    public YiManagementController(YiManagementService yiManagementService){
        this.yiManagementService = yiManagementService;
    }

    /**
     * 去致用邑页面
     * @return ModelAndView
     */
    @RequestMapping(value = YI_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_YI_PAGE);
    }

    /**
     * 致用邑分页
     * @param yiEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = YI_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult yiList(YiEntity yiEntity){
        return yiManagementService.yiList(yiEntity);
    }

    /**
     * 致用邑新增
     * @param yiEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = YI_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO yiAdd(@RequestBody YiEntity yiEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用邑新增,title:{}",yiEntity.getYiTitle());
            }
            yiEntity.setCreateUserId(getLoginId());
            final Boolean flag = yiManagementService.yiAdd(yiEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 致用邑修改
     * @param yiEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = YI_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO yiEdit(@RequestBody YiEntity yiEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用邑修改,title:{}",yiEntity.getYiTitle());
            }
            yiEntity.setUpdateUserId(getLoginId());
            final Boolean flag = yiManagementService.yiEdit(yiEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 致用邑删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = YI_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO yiDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用邑删除,id:{}",id);
            }
            final Boolean flag = yiManagementService.yiDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
