package domain.home.controller;

import domain.home.entity.ProfileEntity;
import domain.home.service.ProfileManagementService;
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

import java.util.List;

import static domain.home.HomeWebForward.TO_PROFILE_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class ProfileManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileManagementController.class);

    final private ProfileManagementService profileManagementService;

    @Autowired
    public ProfileManagementController(ProfileManagementService profileManagementService){
        this.profileManagementService = profileManagementService;
    }

    /**
     * 去学校概况管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = PROFILE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_PROFILE_PAGE);
    }

    /**
     * 学校概况分页
     * @param profileEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = PROFILE_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult profileList(ProfileEntity profileEntity){
        return profileManagementService.profileList(profileEntity);
    }

    /**
     * 学校概况接口
     * @param profileEntity 查询实体
     * @return List<ProfileEntity>
     */
    @RequestMapping(value = "/homepage/profile/list")
    @ResponseBody
    public List<ProfileEntity> profileAllList(ProfileEntity profileEntity){
        return profileManagementService.profileAllList(profileEntity);
    }

    /**
     * 学校概况新增
     * @param profileEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = PROFILE_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO profileAdd(@RequestBody ProfileEntity profileEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        profileEntity.setCreateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("学校概况新增,title:{}",profileEntity.getProfileTitle());
            }
            Boolean flag =  profileManagementService.profileAdd(profileEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 学校概况修改
     * @param profileEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = PROFILE_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO profileEdit(@RequestBody ProfileEntity profileEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        profileEntity.setUpdateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("学校概况修改,title:{}",profileEntity.getProfileTitle());
            }
            Boolean flag =  profileManagementService.profileEdit(profileEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 学校概况删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = PROFILE_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO profileDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("学校概况删除,id:{}",id);
            }
            Boolean flag =  profileManagementService.profileDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
