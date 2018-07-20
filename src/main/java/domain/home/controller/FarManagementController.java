package domain.home.controller;

import domain.home.entity.FarEntity;
import domain.home.service.FarManagementService;
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

import static domain.home.HomeWebForward.TO_FAR_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 致远工作室
 */
@Controller
public class FarManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(FarManagementController.class);

    final private FarManagementService farManagementService;

    @Autowired
    public FarManagementController(FarManagementService farManagementService){
        this.farManagementService = farManagementService;
    }

    /**
     * 去致用工作室页面
     * @return ModelAndView
     */
    @RequestMapping(value = FAR_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_FAR_PAGE);
    }


    /**
     * 致远工作实体分页
     * @param farEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = FAR_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult farList(FarEntity farEntity){
        return farManagementService.farList(farEntity);
    }

    @RequestMapping(value = "/homepage/far/list")
    @ResponseBody
    public List<FarEntity> farAllList(FarEntity farEntity){
        return farManagementService.farAllList(farEntity);
    }

    @RequestMapping(value = "/homepage/far/details/{id}")
    @ResponseBody
    public ModelAndView farDetails(@PathVariable("id") Long id){
        final FarEntity farEntity = farManagementService.farDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",farEntity.getFarTitle());
        map.put("details",farEntity.getFarDetails());
        map.put("createDate",farEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/zygzsdetails",map);
    }

    /**
     * 致用工作室新增
     * @param farEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = FAR_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO farAdd(@RequestBody FarEntity farEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        farEntity.setCreateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用工作室新增,title:{}",farEntity.getFarTitle());
            }
            final Boolean flag = farManagementService.farAdd(farEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 致远工作室修改
     * @param farEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = FAR_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO farEdit(@RequestBody FarEntity farEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        farEntity.setUpdateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用工作室修改,title:{}",farEntity.getFarTitle());
            }
            final Boolean flag = farManagementService.farEdit(farEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 致远工作室删除
     * @return JsonResponseVO
     */
    @RequestMapping(value = FAR_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO farDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("致用工作室删除,id:{}",id);
            }
            final Boolean flag = farManagementService.farDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
