package domain.home.controller;

import domain.home.entity.ResponsibilityEntity;
import domain.home.service.ResponsibilityManagementService;
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

import java.util.List;

import static domain.home.HomeWebForward.TO_RESPONSIBILITY_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class ResponsibilityManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsibilityManagementController.class);

    final private ResponsibilityManagementService responsibilityManagementService;

    @Autowired
    public ResponsibilityManagementController(ResponsibilityManagementService responsibilityManagementService){
        this.responsibilityManagementService = responsibilityManagementService;
    }

    /**
     * 去责任督学页面
     * @return ModelAndView
     */
    @RequestMapping(value = RESPONSIBILITY_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_RESPONSIBILITY_PAGE);
    }

    /**
     * 责任督学分页
     * @param responsibilityEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = RESPONSIBILITY_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult responsibilityList(ResponsibilityEntity responsibilityEntity){
        return responsibilityManagementService.responsibilityList(responsibilityEntity);
    }

    /**
     * 责任督学分页
     * @param responsibilityEntity 查询实体
     * @return List<ResponsibilityEntity>
     */
    @RequestMapping(value = "/homepage/responsibility/list")
    @ResponseBody
    public List<ResponsibilityEntity> responsibilityAllList(ResponsibilityEntity responsibilityEntity){
        return responsibilityManagementService.responsibilityAllList(responsibilityEntity);
    }

    /**
     * 责任督学新增
     * @param responsibilityEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESPONSIBILITY_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO responsibilityAdd(@RequestBody ResponsibilityEntity responsibilityEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        responsibilityEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("责任督学新增,title:{}",responsibilityEntity.getResponsibilityTitle());
            }
            Boolean flag =  responsibilityManagementService.responsibilityAdd(responsibilityEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 责任督学修改
     * @param responsibilityEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESPONSIBILITY_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO responsibilityEdit(@RequestBody ResponsibilityEntity responsibilityEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        responsibilityEntity.setUpdateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("责任督学修改,title:{}",responsibilityEntity.getResponsibilityTitle());
            }
            Boolean flag =  responsibilityManagementService.responsibilityEdit(responsibilityEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 责任督学删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = RESPONSIBILITY_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO responsibilityDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("责任督学删除,id:{}",id);
            }
            Boolean flag =  responsibilityManagementService.responsibilityDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
