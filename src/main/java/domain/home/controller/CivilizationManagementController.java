package domain.home.controller;

import domain.home.entity.CivilizationEntity;
import domain.home.service.CivilizationManagementService;
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

import static domain.home.HomeWebForward.TO_CIVILIZATION_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class CivilizationManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CivilizationManagementController.class);

    final private CivilizationManagementService civilizationManagementService;

    @Autowired
    public CivilizationManagementController(CivilizationManagementService civilizationManagementService){
        this.civilizationManagementService = civilizationManagementService;
    }

    /**
     * 去文明创建页面
     * @return ModelAndView
     */
    @RequestMapping(value = CIVILIZATION_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_CIVILIZATION_PAGE);
    }

    /**
     * 文明创建分页
     * @param civilizationEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = CIVILIZATION_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult civilizationList(CivilizationEntity civilizationEntity){
        return civilizationManagementService.civilizationList(civilizationEntity);
    }

    /**
     * 文明创建新增
     * @param civilizationEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = CIVILIZATION_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO civilizationAdd(@RequestBody CivilizationEntity civilizationEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("文明创建新增,newsTitle:{}",civilizationEntity.getCivilizationTitle());
            }
            civilizationEntity.setCreateUserId(getLoginId());
            final Boolean flag = civilizationManagementService.civilizationAdd(civilizationEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
