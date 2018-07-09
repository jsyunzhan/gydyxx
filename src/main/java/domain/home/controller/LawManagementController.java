package domain.home.controller;

import domain.home.entity.LawEntity;
import domain.home.service.LawManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_LAW_PAGE;
import static domain.home.HomeWebURLMapping.LAW_MANAGEMENT_LIST;
import static domain.home.HomeWebURLMapping.LAW_MANAGEMENT_PAGE;

/**
 * 法制校园
 */
@Controller
public class LawManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(LawManagementController.class);

    final private LawManagementService lawManagementService;

    @Autowired
    public LawManagementController(LawManagementService lawManagementService){
        this.lawManagementService=lawManagementService;
    }

    /**
     * 去法制校园页面
     * @return ModelAndView
     */
    @RequestMapping(value = LAW_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_LAW_PAGE);
    }

    /**
     * 法制校园分页
     * @param lawEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = LAW_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult lawList(LawEntity lawEntity){
        return lawManagementService.lawList(lawEntity);
    }
}
