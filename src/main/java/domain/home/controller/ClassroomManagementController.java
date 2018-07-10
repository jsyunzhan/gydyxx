package domain.home.controller;

import domain.home.entity.ClassroomEntity;
import domain.home.service.ClassroomManagementService;
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

import static domain.home.HomeWebForward.TO_CLASSROOM_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 致用课堂
 */
@Controller
public class ClassroomManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomManagementController.class);

    final private ClassroomManagementService classroomManagementService;

    @Autowired
    public ClassroomManagementController(ClassroomManagementService classroomManagementService){
        this.classroomManagementService = classroomManagementService;
    }

    /**
     * 去致用课堂
     * @return ModelAndView
     */
    @RequestMapping(value = CLASSROOM_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_CLASSROOM_PAGE);
    }

    /**
     * 致用课堂分页
     * @param classroomEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = CLASSROOM_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult classroomList(ClassroomEntity classroomEntity){
        return classroomManagementService.classroomList(classroomEntity);
    }

    /**
     * 致用课堂新增
     * @param classroomEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = CLASSROOM_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO classroomAdd(@RequestBody ClassroomEntity classroomEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用课堂新增,title:{}",classroomEntity.getClassroomTitle());
            }
            classroomEntity.setCreateUserId(getLoginId());
            final Boolean flag = classroomManagementService.classroomAdd(classroomEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 致用课堂修改
     * @param classroomEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = CLASSROOM_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO classroomEdit(@RequestBody ClassroomEntity classroomEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用课堂修改,title:{}",classroomEntity.getClassroomTitle());
            }
            classroomEntity.setUpdateUserId(getLoginId());
            final Boolean flag = classroomManagementService.classroomEdit(classroomEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    @RequestMapping(value = CLASSROOM_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO classroomDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用课堂删除,id:{}",id);
            }
            final Boolean flag = classroomManagementService.classroomDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
