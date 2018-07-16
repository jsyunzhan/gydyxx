package domain.home.controller;

import domain.home.entity.LawEntity;
import domain.home.service.LawManagementService;
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

import java.util.List;

import static domain.home.HomeWebForward.TO_LAW_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 法制校园
 */
@Controller
public class LawManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(LawManagementController.class);

    final private LawManagementService lawManagementService;

    @Autowired
    public LawManagementController(LawManagementService lawManagementService){
        this.lawManagementService=lawManagementService;
    }

    /**
     * 去法制校园页面
     * @return ModelAndView
     */
    @RequestMapping(value = LAW_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_LAW_PAGE);
    }

    /**
     * 法制校园分页
     * @param lawEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = LAW_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult lawList(LawEntity lawEntity){
        return lawManagementService.lawList(lawEntity);
    }

    /**
     * 法制校园查询
     * @param lawEntity 查询实体
     * @return List<LawEntity>
     */
    @RequestMapping(value = "/homepage/law/list")
    @ResponseBody
    public List<LawEntity> lawAllList(LawEntity lawEntity){
        return lawManagementService.lawAllList(lawEntity);
    }

    /**
     * 法制校园新增
     * @param lawEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = LAW_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO lawAdd(@RequestBody LawEntity lawEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("法制校园新增,title:{}",lawEntity.getLawTitle());
            }
            lawEntity.setCreateUserId(getLoginId());
            final Boolean flag = lawManagementService.lawAdd(lawEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 法制校园修改
     * @param lawEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = LAW_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO lawEdit(@RequestBody LawEntity lawEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("法制校园修改,title:{}",lawEntity.getLawTitle());
            }
            lawEntity.setUpdateUserId(getLoginId());
            final Boolean flag = lawManagementService.lawEdit(lawEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 法制校园删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = LAW_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO lawDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("法制校园删除,id:{}",id);
            }
            final Boolean flag = lawManagementService.lawDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
