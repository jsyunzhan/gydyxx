package domain.home.controller;

import domain.home.entity.WorksEntity;
import domain.home.service.WroksManagementService;
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

import static domain.home.HomeWebForward.TO_WROKS_PAGE;
import static domain.home.HomeWebURLMapping.*;


/**
 * 作品管理
 */
@Controller
public class WorksManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(WorksManagementController.class);

    final private WroksManagementService wroksManagementService;

    @Autowired
    public WorksManagementController(WroksManagementService wroksManagementService){
        this.wroksManagementService = wroksManagementService;
    }

    /**
     * 去作品展示页面
     * @return ModelAndView
     */
    @RequestMapping(value = WROKS_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_WROKS_PAGE);
    }

    /**
     * 作品分页
     * @param worksEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = WORKS_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult worksListInfo(WorksEntity worksEntity){
        return wroksManagementService.worksListInfo(worksEntity);
    }

    @RequestMapping(value = "/homepage/works/list")
    @ResponseBody
    public List<WorksEntity> worksAllList(WorksEntity worksEntity){
        return wroksManagementService.worksAllList(worksEntity);
    }

    @RequestMapping(value = "/homepage/works/details/{id}")
    @ResponseBody
    public ModelAndView worksDetails(@PathVariable("id") Long id){
        final WorksEntity worksEntity = wroksManagementService.worksDetails(id);
        final Map<String, Object> map = new HashMap<>(4);
        map.put("title",worksEntity.getWorksTitle());
        map.put("details",worksEntity.getWorksDetails());
        map.put("picturePath",worksEntity.getPicturePath());
        map.put("createDate",worksEntity.getCreateDate());
        return new ModelAndView("pc/zyshaonian/zpzshdetails",map);
    }

    /**
     * 作品新增
     * @param worksEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = WORKS_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO worksAdd(@RequestBody WorksEntity worksEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        worksEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("作品展示新增,title:{}",worksEntity.getWorksTitle());
            }
            Boolean flag =  wroksManagementService.worksAdd(worksEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    /**
     * 作品修改
     * @param worksEntity 修改实体
     * @return jsonResponseVO
     */
    @RequestMapping(value = WORKS_MANAGEMENT_EDIT)
    @ResponseBody
     public JsonResponseVO worksEdit(@RequestBody WorksEntity worksEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
         worksEntity.setUpdateUserId(getLoginId());

         try {
             if (LOGGER.isDebugEnabled()){
                 LOGGER.debug("作品展示修改,title:{}",worksEntity.getWorksTitle());
             }
             Boolean flag =  wroksManagementService.worksEdit(worksEntity);

             jsonResponseVO.setSuccess(flag);
         }catch (Exception e){
             LOGGER.error("业务处理异常:",e);
         }

        return jsonResponseVO;

     }

    /**
     * 作品删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = WORKS_MANAGEMENT_DELETE)
    @ResponseBody
     public JsonResponseVO worksDelete(@PathVariable("id") Long id){
         final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);

         try {
             if (LOGGER.isDebugEnabled()){
                 LOGGER.debug("作品展示删除,id:{}",id);
             }
             final Boolean flag =  wroksManagementService.worksDelete(id,getLoginId());

             jsonResponseVO.setSuccess(flag);
         }catch (Exception e){
             LOGGER.error("业务处理异常:",e);
         }

         return jsonResponseVO;
     }

}
