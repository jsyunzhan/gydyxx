package domain.home.controller;

import domain.home.entity.NewsEntity;
import domain.home.service.NewsManagementService;
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

import static domain.home.HomeWebForward.TO_NEWS_PAGE;
import static domain.home.HomeWebURLMapping.*;

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

    /**
     * 新闻中心接口
     * @param newsEntity 查询实体
     * @return List<NewsEntity>
     */
    @RequestMapping(value = "/homepage/news/list")
    @ResponseBody
    public List<NewsEntity> newsAllList(NewsEntity newsEntity){
        return newsManagementService.newsAllList(newsEntity);
    }

    @RequestMapping(value = "/homepage/news/details/{id}")
    @ResponseBody
    public ModelAndView newsDetails(@PathVariable("id") Long id){
        final NewsEntity newsEntity = newsManagementService.newsDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",newsEntity.getNewsTitle());
        map.put("details",newsEntity.getNewsDetails());
        map.put("picturePath",newsEntity.getPicturePath());
        map.put("createDate",newsEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/xyxwdetails",map);
    }

    /**
     * 新闻中心新增
     * @param newsEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = NEWS_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO newsAdd(@RequestBody NewsEntity newsEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("新闻新增,newsTitle:{}",newsEntity.getNewsTitle());
            }
            newsEntity.setCreateUserId(getLoginId());
            final Boolean flag = newsManagementService.newsAdd(newsEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 新闻修改
     * @param newsEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = NEWS_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO newsEdit(@RequestBody NewsEntity newsEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("新闻修改,newsTitle:{}",newsEntity.getNewsTitle());
            }
            newsEntity.setCreateUserId(getLoginId());
            final Boolean flag = newsManagementService.newsEdit(newsEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 新闻删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = NEWS_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO newsDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("新闻删除,id:{}",id);
            }
            final Boolean flag = newsManagementService.newsDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 设置主标题
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = NEWS_MANAGEMENT_SET_MAIN)
    @ResponseBody
    public JsonResponseVO setMain(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("新闻设置主标题,id:{}",id);
            }
            final Boolean flag = newsManagementService.setMain(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 取消主标题
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = NEWS_MANAGEMENT_CANCEL_MAIN)
    @ResponseBody
    public JsonResponseVO cancelMain(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("新闻取消主标题,id:{}",id);
            }
            final Boolean flag = newsManagementService.cancelMain(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
