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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/homepage/subject/list")
    @ResponseBody
    public List<SubjectEntity> subjectAllList(SubjectEntity subjectEntity){
        return subjectManagementService.subjectAllList(subjectEntity);
    }

    @RequestMapping(value = "/homepage/subject/details/{id}")
    @ResponseBody
    public ModelAndView subjectDetails(@PathVariable("id") Long id){
        final SubjectEntity subjectEntity = subjectManagementService.subjectDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",subjectEntity.getSubjectTitle());
        map.put("details",subjectEntity.getSubjectDetails());
        map.put("createDate",subjectEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/ktyjdetails",map);
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

    /**
     * 课题研究修改
     * @param subjectEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = SUBJECT_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO subjectEdit(@RequestBody SubjectEntity subjectEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        subjectEntity.setUpdateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("课题研究修改,title:{}",subjectEntity.getSubjectTitle());
            }
            Boolean flag =  subjectManagementService.subjectEdit(subjectEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 课题研究删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = SUBJECT_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO subjectDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("课题研究删除,id:{}",id);
            }
            Boolean flag =  subjectManagementService.subjectDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
