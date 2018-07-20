package domain.home.controller;

import domain.home.entity.TeacherEntity;
import domain.home.service.TeacherManagementService;
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

import static domain.home.HomeWebForward.TO_TEACHER_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 名师风采
 */
@Controller
public class TeacherManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherManagementController.class);

    final private TeacherManagementService teacherManagementService;

    @Autowired
    public TeacherManagementController(TeacherManagementService teacherManagementService){
        this.teacherManagementService = teacherManagementService;
    }

    /**
     * 去名师风采页面
     * @return ModelAndView
     */
    @RequestMapping(value = TEACHER_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_TEACHER_PAGE);
    }




    /**
     * 名师风采分页
     * @param teacherEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = TEACHER_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult teacherList(TeacherEntity teacherEntity){
        return teacherManagementService.teacherList(teacherEntity);
    }

    /**
     * 作品展示接口
     * @param teacherEntity 查询实体
     * @return List<TeacherEntity>
     */
    @RequestMapping(value = "/homepage/teacher/list")
    @ResponseBody
    public List<TeacherEntity> teacherAllList(TeacherEntity teacherEntity){
        return teacherManagementService.teacherAllList(teacherEntity);
    }

    @RequestMapping(value = "/homepage/teacher/details/{id}")
    @ResponseBody
    public ModelAndView teacherDetails(@PathVariable("id") Long id){
        final TeacherEntity teacherEntity = teacherManagementService.teacherDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",teacherEntity.getTeacherTitle());
        map.put("details",teacherEntity.getTeacherDetails());
        map.put("picturePath",teacherEntity.getPicturePath());
        map.put("createDate",teacherEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/msfcdetails",map);
    }


    /**
     * 名师风采新增
     * @param teacherEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = TEACHER_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO teacherAdd(@RequestBody TeacherEntity teacherEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        teacherEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("名师风采新增,title:{}",teacherEntity.getTeacherTitle());
            }
            Boolean flag =  teacherManagementService.teacherAdd(teacherEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 名师风采修改
     * @param teacherEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = TEACHER_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO teacherEdit(@RequestBody TeacherEntity teacherEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        teacherEntity.setUpdateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("名师风采修改,title:{}",teacherEntity.getTeacherTitle());
            }
            Boolean flag =  teacherManagementService.teacherEdit(teacherEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 名师风采删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = TEACHER_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO teacherDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("名师风采删除,id:{}",id);
            }
            Boolean flag =  teacherManagementService.teacherDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
