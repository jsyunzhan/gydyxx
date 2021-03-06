package domain.home.controller;

import domain.home.entity.RulesEntity;
import domain.home.service.RulesManagementService;
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

import static domain.home.HomeWebForward.TO_RULES_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class RulesManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(RulesManagementController.class);

    final private RulesManagementService rulesManagementService;

    @Autowired
    public RulesManagementController(RulesManagementService rulesManagementService){
        this.rulesManagementService = rulesManagementService;
    }

    /**
     * 去规章制度页面
     * @return ModelAndView
     */
    @RequestMapping(value = RULES_MANAGEMENT_PAGE)
    @ResponseBody
    public ModelAndView index(){
        return new ModelAndView(TO_RULES_PAGE);
    }

    /**
     * 规章制度分页
     * @param rulesEntity rulesEntity
     * @return PageQueryResult
     */
    @RequestMapping(value = RULES_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult rulesList(RulesEntity rulesEntity){
        return rulesManagementService.rulesList(rulesEntity);
    }

    /**
     * 规章制度接口
     * @param rulesEntity 查询实体
     * @return List<RulesEntity>
     */
    @RequestMapping(value = "/homepage/rules/list")
    @ResponseBody
    public List<RulesEntity> rulesAllList(RulesEntity rulesEntity){
        return rulesManagementService.rulesAllList(rulesEntity);
    }

    @RequestMapping(value = "/homepage/rules/details/{id}")
    @ResponseBody
    public ModelAndView rulesDetails(@PathVariable("id") Long id){
        final RulesEntity rulesEntity = rulesManagementService.rulesDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",rulesEntity.getRulesTitle());
        map.put("details",rulesEntity.getRulesDetails());
        map.put("createDate",rulesEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/gzzddetails",map);
    }

    /**
     * 规章制度新增
     * @param rulesEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RULES_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO rulesAdd(@RequestBody RulesEntity rulesEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        rulesEntity.setCreateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("规章制度新增,title:{}",rulesEntity.getRulesTitle());
            }
            Boolean flag =  rulesManagementService.rulesAdd(rulesEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 规章制度修改
     * @param rulesEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = RULES_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO rulesEdit(@RequestBody RulesEntity rulesEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("规章制度修改,title:{}",rulesEntity.getRulesTitle());
            }
            Boolean flag =  rulesManagementService.rulesEdit(rulesEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 规章制度删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = RULES_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO rulesDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("规章制度删除,id:{}",id);
            }
            Boolean flag =  rulesManagementService.rulesDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
