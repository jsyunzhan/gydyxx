package domain.home.controller;

import domain.home.entity.SubjectEntity;
import domain.home.service.SubjectManagementService;
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

import static domain.home.HomeWebForward.TO_SUBJECT_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class SubjectManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectManagementController.class);

    final private SubjectManagementService subjectManagementService;

    @Autowired
    public SubjectManagementController(SubjectManagementService subjectManagementService){
        this.subjectManagementService = subjectManagementService;
    }

    /**
     * 去课题研究页面
     * @return ModelAndView
     */
    @RequestMapping(value = SUBJECT_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_SUBJECT_PAGE);
    }

    /**
     * 课题研究分页
     * @param subjectEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = SUBJECT_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult subjectList(SubjectEntity subjectEntity){
        return subjectManagementService.subjectList(subjectEntity);
    }

    /**
     * 课题研究新增
     * @param subjectEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = SUBJECT_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO subjectAdd(@RequestBody SubjectEntity subjectEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        subjectEntity.setCreateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("课题研究新增,title:{}",subjectEntity.getSubjectTitle());
            }
            Boolean flag =  subjectManagementService.subjectAdd(subjectEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}