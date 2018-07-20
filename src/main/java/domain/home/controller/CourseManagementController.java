package domain.home.controller;

import domain.home.entity.CourseEntity;
import domain.home.service.CourseManagementService;
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

import static domain.home.HomeWebForward.TO_COURSE_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 班本课程
 */
@Controller
public class CourseManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseManagementController.class);

    final private CourseManagementService courseManagementService;

    @Autowired
    public CourseManagementController(CourseManagementService courseManagementService){
        this.courseManagementService = courseManagementService;
    }

    /**
     * 去班本课程页面
     * @return ModelAndView
     */
    @RequestMapping(value = COURSE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_COURSE_PAGE);
    }

    /**
     * 班本课程分页
     * @param courseEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = COURSE_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult courseList(CourseEntity courseEntity){
        return courseManagementService.courseList(courseEntity);
    }

    @RequestMapping(value = "/homepage/course/list")
    @ResponseBody
    public List<CourseEntity> courseAllList(CourseEntity courseEntity){
        return courseManagementService.courseAllList(courseEntity);
    }

    @RequestMapping(value = "/homepage/course/details/{id}")
    @ResponseBody
    public ModelAndView courseDetails(@PathVariable("id") Long id){
        final CourseEntity courseEntity = courseManagementService.courseDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",courseEntity.getCourseTitle());
        map.put("details",courseEntity.getCourseDetails());
        map.put("picturePath",courseEntity.getPicturePath());
        map.put("createDate",courseEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/bbkcdetails",map);
    }

    /**
     * 班本课程新增
     * @param courseEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = COURSE_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO courseAdd(@RequestBody CourseEntity courseEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("班本课程新增,title:{}",courseEntity.getCourseTitle());
            }
            courseEntity.setCreateUserId(getLoginId());
            final Boolean flag = courseManagementService.courseAdd(courseEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 班本课程修改
     * @param courseEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = COURSE_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO courseEdit(@RequestBody CourseEntity courseEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("班本课程修改,title:{}",courseEntity.getCourseTitle());
            }
            courseEntity.setUpdateUserId(getLoginId());
            final Boolean flag = courseManagementService.courseEdit(courseEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 班本课程删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = COURSE_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO courseDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("班本课程删除,id:{}",id);
            }
            final Boolean flag = courseManagementService.courseDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
