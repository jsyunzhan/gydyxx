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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
