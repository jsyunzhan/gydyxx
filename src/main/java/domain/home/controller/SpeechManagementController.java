package domain.home.controller;

import domain.home.entity.SpeechEntity;
import domain.home.service.SpeechManagementService;
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

import static domain.home.HomeWebForward.TO_SPEECH_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 国旗下讲话
 */
@Controller
public class SpeechManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechManagementController.class);

    final private SpeechManagementService speechManagementService;

    @Autowired
    public SpeechManagementController(SpeechManagementService speechManagementService){
        this.speechManagementService = speechManagementService;
    }


    /**
     * 去国旗下讲话管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = SPEECH_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_SPEECH_PAGE);
    }

    /**
     * 国旗下讲话分页
     * @param speechEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = SPEECH_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult speechList(SpeechEntity speechEntity){
        return speechManagementService.speechList(speechEntity);
    }

    /**
     * 国旗下讲话新增
     * @param speechEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = SPEECH_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO speechAdd(@RequestBody SpeechEntity speechEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("国旗下讲话新增,title:{}",speechEntity.getSpeechTitle());
            }
            speechEntity.setCreateUserId(getLoginId());
            final Boolean flag = speechManagementService.speechAdd(speechEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 国旗下讲话修改
     * @param speechEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = SPEECH_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO speechEdit(@RequestBody SpeechEntity speechEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("国旗下讲话修改,title:{}",speechEntity.getSpeechTitle());
            }
            speechEntity.setCreateUserId(getLoginId());
            final Boolean flag = speechManagementService.speechEdit(speechEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 国旗下讲话删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = SPEECH_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO speechDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("国旗下讲话删除,id:{}",id);
            }

            final Boolean flag = speechManagementService.speechDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
