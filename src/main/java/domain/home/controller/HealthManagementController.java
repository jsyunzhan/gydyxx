package domain.home.controller;

import domain.home.entity.HealthEntity;
import domain.home.service.HealthManagementService;
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

import static domain.home.HomeWebForward.TO_HEALTH_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 健康教育
 */
@Controller
public class HealthManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthManagementController.class);

    final private HealthManagementService healthManagementService;

    @Autowired
    public HealthManagementController(HealthManagementService healthManagementService){
        this.healthManagementService = healthManagementService;
    }

    /**
     * 去健康教育页面
     * @return ModelAndView
     */
    @RequestMapping(value = HEALTH_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_HEALTH_PAGE);
    }

    /**
     * 健康教育分页
     * @param healthEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = HEALTH_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult healthList(HealthEntity healthEntity){
        return healthManagementService.healthList(healthEntity);
    }

    @RequestMapping(value = "/homepage/health/list")
    @ResponseBody
    public List<HealthEntity> healthAllList(HealthEntity healthEntity){
        return healthManagementService.healthAllList(healthEntity);
    }

    @RequestMapping(value = "/homepage/health/details/{id}")
    @ResponseBody
    public ModelAndView healthDetails(@PathVariable("id") Long id){
        final HealthEntity healthEntity = healthManagementService.healthDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",healthEntity.getHealthTitle());
        map.put("details",healthEntity.getHealthDetails());
        map.put("createDate",healthEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/jkjydetails",map);
    }

    /**
     * 健康教育新增
     * @param healthEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = HEALTH_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO healthAdd(@RequestBody HealthEntity healthEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("健康教育新增,title:{}",healthEntity.getHealthTitle());
            }
            healthEntity.setCreateUserId(getLoginId());
            final Boolean flag = healthManagementService.healthAdd(healthEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 健康教育修改
     * @param healthEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = HEALTH_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO healthEdit(@RequestBody HealthEntity healthEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("健康教育修改,title:{}",healthEntity.getHealthTitle());
            }
            healthEntity.setUpdateUserId(getLoginId());
            final Boolean flag = healthManagementService.healthEdit(healthEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 健康教育删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = HEALTH_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO healthDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("健康教育删除,id:{}",id);
            }
            final Boolean flag = healthManagementService.healthDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
