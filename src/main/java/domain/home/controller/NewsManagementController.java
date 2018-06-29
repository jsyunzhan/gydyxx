package domain.home.controller;

import domain.home.entity.NewsEntity;
import domain.home.service.NewsManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_NEWS_PAGE;
import static domain.home.HomeWebURLMapping.NEWS_MANAGEMENT_LIST;
import static domain.home.HomeWebURLMapping.NEWS_MANAGEMENT_PAGE;

@Controller
public class NewsManagementController extends AbstractActionController{

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsManagementController.class);

    private final NewsManagementService newsManagementService;

    @Autowired
    public NewsManagementController(NewsManagementService newsManagementService){
        this.newsManagementService = newsManagementService;
    }

    /**
     * 去新闻管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = NEWS_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_NEWS_PAGE);
    }

    /**
     * 新闻中心分页
     * @param newsEntity newsEntity
     * @return PageQueryResult
     */
    @RequestMapping(value = NEWS_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult newsListInfo(NewsEntity newsEntity){
        return newsManagementService.newsListInfo(newsEntity);
    }
}
