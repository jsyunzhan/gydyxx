package domain.home.controller;

import domain.home.entity.StudentEntity;
import domain.home.service.StudentManagementService;
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

import static domain.home.HomeWebForward.TO_STUDENT_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 学子风采
 */
@Controller
public class StudentManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementController.class);

    final private StudentManagementService studentManagementService;

    @Autowired
    public StudentManagementController(StudentManagementService studentManagementService){
        this.studentManagementService = studentManagementService;
    }

    /**
     * 去学子管理页面
     * @return 学子管理页面
     */
    @RequestMapping(value = STUDENT_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_STUDENT_PAGE);
    }

    /**
     * 学子风采分页
     * @param studentEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = STUDENT_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult studentList(StudentEntity studentEntity){
        return studentManagementService.studentList(studentEntity);
    }

    /**
     * 学子风采新增
     * @param studentEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = STUDENT_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO studentAdd(@RequestBody StudentEntity studentEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        studentEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("学子风采新增,title:{}",studentEntity.getStudentTitle());
            }
            Boolean flag =  studentManagementService.studentAdd(studentEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
