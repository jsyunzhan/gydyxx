package domain.home.controller;

import domain.home.entity.CommunityEntity;
import domain.home.service.CommunityManagementService;
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

import static domain.home.HomeWebForward.TO_COMMUNITY_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 精品社团
 */
@Controller
public class CommunityManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityManagementController.class);

    final private CommunityManagementService communityManagementService;

    @Autowired
    public CommunityManagementController(CommunityManagementService communityManagementService){
        this.communityManagementService = communityManagementService;
    }

    /**
     * 去精品社团页面
     * @return ModelAndView
     */
    @RequestMapping(value = COMMUNITY_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_COMMUNITY_PAGE);
    }

    /**
     * 精品社团分页
     * @param communityEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = COMMUNITY_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult communityList(CommunityEntity communityEntity){
        return communityManagementService.communityList(communityEntity);
    }

    @RequestMapping(value = "/homepage/community/list")
    @ResponseBody
    public List<CommunityEntity> communityAllList(CommunityEntity communityEntity){
        return communityManagementService.communityAllList(communityEntity);
    }

    @RequestMapping(value = "/homepage/community/details/{id}")
    @ResponseBody
    public ModelAndView communityDetails(@PathVariable("id") Long id){
        final CommunityEntity communityEntity = communityManagementService.communityDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",communityEntity.getCommunityTitle());
        map.put("details",communityEntity.getCommunityDetails());
        map.put("picturePath",communityEntity.getPicturePath());
        map.put("createDate",communityEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/jpstdetails",map);
    }

    /**
     * 精品社团新增
     * @param communityEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = COMMUNITY_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO communityAdd(@RequestBody CommunityEntity communityEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("精品社团新增,title:{}",communityEntity.getCommunityTitle());
            }
            communityEntity.setCreateUserId(getLoginId());
            final Boolean flag = communityManagementService.communityAdd(communityEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 精品社团修改
     * @param communityEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = COMMUNITY_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO communityEdit(@RequestBody CommunityEntity communityEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("精品社团修改,title:{}",communityEntity.getCommunityTitle());
            }
            communityEntity.setUpdateUserId(getLoginId());
            final Boolean flag = communityManagementService.communityEdit(communityEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 精品校园删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = COMMUNITY_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO communityDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("精品社团删除,id:{}",id);
            }
            final Boolean flag = communityManagementService.communityDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
