package domain.home.controller;

import domain.home.service.NoticeManagementService;
import domain.shiro.controller.AbstractActionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_NOTICE_PAGE;
import static domain.home.HomeWebURLMapping.NOTICE_MANAGEMENT_PAGE;

@Controller
public class NoticeManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeManagementController.class);

    final private NoticeManagementService noticeManagementService;

    @Autowired
    public NoticeManagementController(NoticeManagementService noticeManagementService){
        this.noticeManagementService = noticeManagementService;
    }

    @RequestMapping(value = NOTICE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_NOTICE_PAGE);
    }
}
