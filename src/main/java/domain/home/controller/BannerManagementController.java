package domain.home.controller;

import domain.home.entity.BannerEntity;
import domain.home.service.BannerManagementService;
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

import static domain.home.HomeWebForward.TO_BANNER_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 轮播图
 */
@Controller
public class BannerManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(BannerManagementController.class);

    final private BannerManagementService bannerManagementService;

    @Autowired
    public BannerManagementController(BannerManagementService bannerManagementService){
        this.bannerManagementService = bannerManagementService;
    }

    @RequestMapping(value = BANNER_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_BANNER_PAGE);
    }

    /**
     * 轮播图分页
     * @param bannerEntity 查询实体
     * @return PageQueryResult
     */
    @RequestMapping(value = BANNER_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult bannerList(BannerEntity bannerEntity){
        return bannerManagementService.bannerList(bannerEntity);
    }

    @RequestMapping(value = "/homepage/banner/list")
    @ResponseBody
    public List<BannerEntity> bannerAllList(BannerEntity bannerEntity){
        return bannerManagementService.bannerAllList(bannerEntity);
    }


    @RequestMapping(value = "/homepage/banner/details/{id}")
    @ResponseBody
    public ModelAndView bannerDetails(@PathVariable("id") Long id){
        final BannerEntity bannerEntity = bannerManagementService.bannerDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",bannerEntity.getBannerTitle());
        map.put("details",bannerEntity.getBannerDetails());
        map.put("picturePath",bannerEntity.getPicturePath());
        map.put("createDate",bannerEntity.getCreateDate());
        return new ModelAndView("pc/search/banner",map);
    }

    /**
     * 轮播图新增
     * @param bannerEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = BANNER_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO bannerAdd(@RequestBody BannerEntity bannerEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("轮播图新增,title:{}",bannerEntity.getBannerTitle());
            }
            bannerEntity.setCreateUserId(getLoginId());
            final Boolean flag = bannerManagementService.bannerAdd(bannerEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 轮播图修改
     * @param bannerEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = BANNER_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO bannerEdit(@RequestBody BannerEntity bannerEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("轮播图修改,title:{}",bannerEntity.getBannerTitle());
            }
            bannerEntity.setUpdateUserId(getLoginId());
            final Boolean flag = bannerManagementService.bannerEdit(bannerEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 轮播图删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = BANNER_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO bannerDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("轮播图删除,id:{}",id);
            }
            final Boolean flag = bannerManagementService.bannerDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 轮播图开启
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = BANNER_MANAGEMENT_OPEN)
    @ResponseBody
    public JsonResponseVO bannerOpen(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("轮播图开启,id:{}",id);
            }
            final Boolean flag = bannerManagementService.bannerOpen(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 轮播图暂停
     * @return JsonResponseVO
     */
    @RequestMapping(value = BANNER_MANAGEMENT_CLOSE)
    @ResponseBody
    public JsonResponseVO bannerClose(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("轮播图暂停,id:{}",id);
            }
            final Boolean flag = bannerManagementService.bannerClose(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

}
