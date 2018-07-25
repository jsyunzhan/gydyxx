package domain.home.controller;

import domain.home.entity.EmailEntity;
import domain.home.service.EmailManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static domain.home.HomeWebForward.TO_EMAIL_PAGE;
import static domain.home.HomeWebURLMapping.EMAIL_MANAGEMENT_LIST;
import static domain.home.HomeWebURLMapping.EMAIL_MANAGEMENT_PAGE;

/**
 * 校长信箱
 */
@Controller
public class EmailManagementController extends AbstractActionController{

    final private EmailManagementService emailManagementService;

    @Autowired
    public EmailManagementController(EmailManagementService emailManagementService){
        this.emailManagementService = emailManagementService;
    }

    /**
     * 去校长信箱管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = EMAIL_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_EMAIL_PAGE);
    }

    /**
     * 校长信箱分页
     * @param emailEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = EMAIL_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult emailList(EmailEntity emailEntity){
        return emailManagementService.emailList(emailEntity);
    }

    @RequestMapping(value = "/homepage/email/list")
    @ResponseBody
    public List<EmailEntity> emailAllList(EmailEntity emailEntity){
        return emailManagementService.emailAllList(emailEntity);
    }

    @RequestMapping(value = "/homepage/email/add")
    @ResponseBody
    public JsonResponseVO emailAdd(@RequestBody EmailEntity emailEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        Boolean flag = emailManagementService.emailAdd(emailEntity);
        jsonResponseVO.setSuccess(flag);
        return jsonResponseVO;
    }
}
