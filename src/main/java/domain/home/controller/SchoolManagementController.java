package domain.home.controller;

import domain.home.entity.SchoolEntity;
import domain.home.service.SchoolManagementService;
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

import static domain.home.HomeWebForward.TO_SCHOOL_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class SchoolManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolManagementController.class);

    final private SchoolManagementService schoolManagementService;

    @Autowired
    public SchoolManagementController(SchoolManagementService schoolManagementService){
        this.schoolManagementService = schoolManagementService;
    }

    /**
     * 去学校风采页面
     * @return ModelAndView
     */
    @RequestMapping(value = SCHOOL_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_SCHOOL_PAGE);
    }

    /**
     * 学校风采分页
     * @param schoolEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = SCHOOL_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult schoolList(SchoolEntity schoolEntity){
        return schoolManagementService.schoolList(schoolEntity);
    }

    /**
     * 学校风采新增
     * @param schoolEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = SCHOOL_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO schoolAdd(@RequestBody SchoolEntity schoolEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("学校风采新增,title:{}",schoolEntity.getSchoolTitle());
            }
            Boolean flag =  schoolManagementService.schoolAdd(schoolEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    @RequestMapping(value = SCHOOL_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO schoolEdit(@RequestBody SchoolEntity schoolEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("学校风采修改,title:{}",schoolEntity.getSchoolTitle());
            }
            Boolean flag =  schoolManagementService.schoolEdit(schoolEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
