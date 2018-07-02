package domain.home.controller;

import domain.home.service.WroksManagementService;
import domain.shiro.controller.AbstractActionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_WROKS_PAGE;
import static domain.home.HomeWebURLMapping.WROKS_MANAGEMENT_PAGE;


/**
 * 作品管理
 */
@Controller
public class WorksManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(WorksManagementController.class);

    final private WroksManagementService wroksManagementService;

    @Autowired
    public WorksManagementController(WroksManagementService wroksManagementService){
        this.wroksManagementService = wroksManagementService;
    }

    @RequestMapping(value = WROKS_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_WROKS_PAGE);
    }
}
