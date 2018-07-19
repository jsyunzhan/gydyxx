package domain.home.controller;

import domain.home.entity.PartyEntity;
import domain.home.service.PartyManagementService;
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

import static domain.home.HomeWebForward.TO_PARTY_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class PartyManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(PartyManagementController.class);

    final private PartyManagementService partyManagementService;

    @Autowired
    public PartyManagementController(PartyManagementService partyManagementService){
        this.partyManagementService = partyManagementService;
    }

    /**
     * 去党建工会页面
     * @return ModelAndView
     */
    @RequestMapping(value = PARTY_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_PARTY_PAGE);
    }

    /**
     * 党建工会分页
     * @param partyEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = PARTY_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult partyList(PartyEntity partyEntity){
        return partyManagementService.partyList(partyEntity);
    }

    /**
     * 党建工会接口
     * @param partyEntity 分页实体
     * @return List<PartyEntity>
     */
    @RequestMapping(value = "/homepage/party/list")
    @ResponseBody
    public List<PartyEntity> partyAllList(PartyEntity partyEntity){
        return partyManagementService.partyAllList(partyEntity);
    }

    @RequestMapping(value = "/homepage/party/details/{id}")
    @ResponseBody
    public ModelAndView partyDetails(@PathVariable("id") Long id){
        final PartyEntity partyEntity = partyManagementService.partyDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",partyEntity.getPartyTitle());
        map.put("details",partyEntity.getPartyDetails());
        map.put("createDate",partyEntity.getCreateDate());
        return new ModelAndView("pc/zyxiaoyuan/djghdetails",map);
    }

    /**
     * 党建工会新增
     * @param partyEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = PARTY_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO partyAdd(@RequestBody PartyEntity partyEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        partyEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("党建工会新增,title:{}",partyEntity.getPartyTitle());
            }
            Boolean flag =  partyManagementService.partyAdd(partyEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 党建工会修改
     * @param partyEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = PARTY_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO partyEdit(@RequestBody PartyEntity partyEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        partyEntity.setUpdateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("党建工会修改,title:{}",partyEntity.getPartyTitle());
            }
            Boolean flag =  partyManagementService.partyEdit(partyEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    @RequestMapping(value = PARTY_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO partyDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("党建工会删除,id:{}",id);
            }
            Boolean flag =  partyManagementService.partyDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
