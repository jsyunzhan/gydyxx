package domain.home.controller;

import domain.home.entity.EducationEntity;
import domain.home.service.EducationManagementService;
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

import static domain.home.HomeWebForward.TO_EDUCATION_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 教育科研
 */
@Controller
public class EducationManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationManagementController.class);

    final private EducationManagementService educationManagementService;

    @Autowired
    public EducationManagementController(EducationManagementService educationManagementService){
        this.educationManagementService = educationManagementService;
    }

    /**
     * 去教育科研页面
     * @return ModelAndView
     */
    @RequestMapping(value = EDUCATION_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_EDUCATION_PAGE);
    }

    /**
     * 教育科研分页
     * @param educationEntity 分页
     * @return PageQueryResult
     */
    @RequestMapping(value = EDUCATION_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult educationList(EducationEntity educationEntity){
        return educationManagementService.educationList(educationEntity);
    }

    @RequestMapping(value = "/homepage/education/list")
    @ResponseBody
    public List<EducationEntity> educationAllList(EducationEntity educationEntity){
        return educationManagementService.educationAllList(educationEntity);
    }

    @RequestMapping(value = "/homepage/education/details/{id}")
    @ResponseBody
    public ModelAndView educationDetails(@PathVariable("id") Long id){
        final EducationEntity educationEntity = educationManagementService.educationDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",educationEntity.getEducationTitle());
        map.put("details",educationEntity.getEducationDetails());
        map.put("createDate",educationEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/jykydetails",map);
    }

    /**
     * 教育科研新增
     * @param educationEntity 新增
     * @return JsonResponseVO
     */
    @RequestMapping(value = EDUCATION_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO educationAdd(@RequestBody EducationEntity educationEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        educationEntity.setCreateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("教育科研新增,title:{}",educationEntity.getEducationTitle());
            }
            Boolean flag =  educationManagementService.educationAdd(educationEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 教育科研修改
     * @param educationEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = EDUCATION_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO educationEdit(@RequestBody EducationEntity educationEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        educationEntity.setUpdateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("教育科研修改,title:{}",educationEntity.getEducationTitle());
            }
            Boolean flag =  educationManagementService.educationEdit(educationEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 教育科研删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = EDUCATION_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO educationDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("教育科研删除,title:{}",id);
            }
            Boolean flag =  educationManagementService.educationDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
