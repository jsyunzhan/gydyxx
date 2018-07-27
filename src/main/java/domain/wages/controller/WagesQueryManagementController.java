package domain.wages.controller;

import domain.shiro.controller.AbstractActionController;
import domain.wages.entity.WagesEntity;
import domain.wages.service.WagesQueryManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static domain.wages.WagesWebForward.TO_WAGES_QUERY_PAGE;
import static domain.wages.WagesWebURLMapping.WAGES_QUERY_MANAGEMENT_LIST;
import static domain.wages.WagesWebURLMapping.WAGES_QUERY_MANAGEMENT_PAGE;

@Controller
public class WagesQueryManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(WagesQueryManagementController.class);

    private WagesQueryManagementService wagesQueryManagementService;

    @Autowired
    public WagesQueryManagementController(WagesQueryManagementService wagesQueryManagementService){
        this.wagesQueryManagementService = wagesQueryManagementService;
    }

    /**
     * 去工资查询页面
     * @return ModelAndView
     */
    @RequestMapping(value = WAGES_QUERY_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_WAGES_QUERY_PAGE);
    }

    /**
     * 查询自己的工资
     * @return List<WagesEntity>
     */
    @RequestMapping(value = WAGES_QUERY_MANAGEMENT_LIST)
    @ResponseBody
    public List<WagesEntity> wagesList(){
        return wagesQueryManagementService.wagesList(getLoginId());
    }
}
