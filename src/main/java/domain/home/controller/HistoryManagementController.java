package domain.home.controller;

import domain.home.entity.HistoryEntity;
import domain.home.service.HistoryManagementService;
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

import static domain.home.HomeWebForward.TO_HISTORY_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class HistoryManagementController extends AbstractActionController{

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryManagementController.class);

    final private HistoryManagementService historyManagementService;

    @Autowired
    public HistoryManagementController(HistoryManagementService historyManagementService){
        this.historyManagementService = historyManagementService;
    }

    /**
     * 去校史天地页面
     * @return ModelAndView
     */
    @RequestMapping(value = HISTORY_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_HISTORY_PAGE);
    }

    /**
     * 校史天地分页
     * @param historyEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = HISTORY_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult historyList(HistoryEntity historyEntity){
        return historyManagementService.historyList(historyEntity);
    }

    @RequestMapping(value = "/homepage/history/list")
    @ResponseBody
    public List<HistoryEntity> historyAllList(HistoryEntity historyEntity){
        return historyManagementService.historyAllList(historyEntity);
    }

    @RequestMapping(value = "/homepage/history/details/{id}")
    @ResponseBody
    public ModelAndView historyDetails(@PathVariable("id") Long id){
        final HistoryEntity historyEntity = historyManagementService.historyDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",historyEntity.getHistoryTitle());
        map.put("details",historyEntity.getHistoryDetails());
        map.put("picturePath",historyEntity.getPicturePath());
        map.put("createDate",historyEntity.getCreateDate());
        return new ModelAndView("pc/xstiandi/xstddetails",map);
    }

    /**
     * 校史天地新增
     * @param historyEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = HISTORY_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO historyAdd(@RequestBody HistoryEntity historyEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校史天地新增,historyTitle:{}",historyEntity.getHistoryTitle());
            }
            historyEntity.setCreateUserId(getLoginId());
            final Boolean flag = historyManagementService.historyAdd(historyEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 校史天地修改
     * @param historyEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = HISTORY_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO historyEdit(@RequestBody HistoryEntity historyEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校史天地修改,historyTitle:{}",historyEntity.getHistoryTitle());
            }
            historyEntity.setUpdateUserId(getLoginId());
            final Boolean flag = historyManagementService.historyEdit(historyEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 校史天地删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = HISTORY_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO historyDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校史天地删除,di:{}",id);
            }
            final Boolean flag = historyManagementService.historyDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
