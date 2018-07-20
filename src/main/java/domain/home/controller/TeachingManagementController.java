package domain.home.controller;

import domain.home.entity.TeachingEntity;
import domain.home.service.TeachingManagementService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.home.HomeWebForward.TO_TEACHING_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 教学资源
 */
@Controller
public class TeachingManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingManagementController.class);

    final private TeachingManagementService teachingManagementService;

    @Autowired
    public TeachingManagementController(TeachingManagementService teachingManagementService){
        this.teachingManagementService = teachingManagementService;
    }

    /**
     * 去教学资源管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = TEACHING_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_TEACHING_PAGE);
    }

    /**
     * 教学资源分页
     * @param teachingEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = TEACHING_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult teachingList(TeachingEntity teachingEntity){
        return teachingManagementService.teachingList(teachingEntity);
    }

    @RequestMapping(value = "/homepage/teaching/list")
    @ResponseBody
    public List<TeachingEntity> teachingAllList(TeachingEntity teachingEntity){
        return teachingManagementService.teachingAllList(teachingEntity);
    }

    @RequestMapping(value = "/homepage/teaching/details/{id}")
    @ResponseBody
    public ModelAndView teacheringDetails(@PathVariable("id") Long id){
        final TeachingEntity teachingEntity = teachingManagementService.teacheringDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",teachingEntity.getTeachingTitle());
        map.put("details",teachingEntity.getTeachingDetails());
        map.put("createDate",teachingEntity.getCreateDate());
        return new ModelAndView("pc/zyyuanding/jxziyuandetails",map);
    }

    /**
     * 教学资源新增
     * @param teachingEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = TEACHING_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO teachingAdd(@RequestBody TeachingEntity teachingEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("教学资源新增,title:{}",teachingEntity.getTeachingTitle());
            }
            teachingEntity.setCreateUserId(getLoginId());
            final Boolean flag = teachingManagementService.teachingAdd(teachingEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 教学资源修改
     * @param teachingEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = TEACHING_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO teachingEdit(@RequestBody TeachingEntity teachingEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("教学资源修改,title:{}",teachingEntity.getTeachingTitle());
            }
            teachingEntity.setUpdateUserId(getLoginId());
            final Boolean flag = teachingManagementService.teachingEdit(teachingEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 教学资源删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = TEACHING_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO teachingDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("教学资源删除,id:{}",id);
            }
            final Boolean flag = teachingManagementService.teachingDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
