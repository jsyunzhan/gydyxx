package domain.home.controller;

import domain.home.entity.CelebrateEntity;
import domain.home.service.CelebrateManagementService;
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

import static domain.home.HomeWebForward.TO_CELEBRATE_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 校园节庆
 */
@Controller
public class CelebrateManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(CelebrateManagementController.class);

    final private CelebrateManagementService celebrateManagementService;

    @Autowired
    public CelebrateManagementController(CelebrateManagementService celebrateManagementService){
        this.celebrateManagementService = celebrateManagementService;
    }

    /**
     * 去校园节庆页面
     * @return ModelAndView
     */
    @RequestMapping(value = CELEBRATE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_CELEBRATE_PAGE);
    }

    /**
     * 校园节庆
     * @param celebrateEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = CELEBRATE_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult celebrateList(CelebrateEntity celebrateEntity){
        return celebrateManagementService.celebrateList(celebrateEntity);
    }

    @RequestMapping(value = "/homepage/celebrate/list")
    @ResponseBody
    public List<CelebrateEntity> celebrateAllList(CelebrateEntity celebrateEntity){
        return celebrateManagementService.celebrateAllList(celebrateEntity);
    }

    /**
     * 校园节庆新增
     * @param celebrateEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = CELEBRATE_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO celebrateAdd(@RequestBody CelebrateEntity celebrateEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校园节庆新增,title:{}",celebrateEntity.getCelebrateTitle());
            }
            celebrateEntity.setCreateUserId(getLoginId());
            final Boolean flag = celebrateManagementService.celebrateAdd(celebrateEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 校园节庆修改
     * @param celebrateEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = CELEBRATE_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO celebrateEdit(@RequestBody CelebrateEntity celebrateEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校园节庆修改,title:{}",celebrateEntity.getCelebrateTitle());
            }
            celebrateEntity.setUpdateUserId(getLoginId());
            final Boolean flag = celebrateManagementService.celebrateEdit(celebrateEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 校园节庆删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = CELEBRATE_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO celebrateDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校园节庆删除,id:{}",id);
            }
            final Boolean flag = celebrateManagementService.celebrateDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
