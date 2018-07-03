package domain.home.controller;

import domain.home.entity.TeacherEntity;
import domain.home.service.TeacherManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_TEACHER_PAGE;
import static domain.home.HomeWebURLMapping.TEACHER_MANAGEMENT_LIST;
import static domain.home.HomeWebURLMapping.TEACHER_MANAGEMENT_PAGE;

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
}
