package domain.home.controller;

import domain.home.entity.TrainingEntity;
import domain.home.service.TrainingManagementService;
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

import static domain.home.HomeWebForward.TO_TRAINING_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 校本培训
 */
@Controller
public class TrainingManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingManagementController.class);

    final private TrainingManagementService trainingManagementService;

    @Autowired
    public TrainingManagementController(TrainingManagementService trainingManagementService){
        this.trainingManagementService = trainingManagementService;
    }

    /**
     * 去校本培训页面
     * @return ModelAndView
     */
    @RequestMapping(value = TRAINING_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_TRAINING_PAGE);
    }

    /**
     * 校本培训分页
     * @param trainingEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = TRAINING_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult trainingList(TrainingEntity trainingEntity){
        return trainingManagementService.trainingList(trainingEntity);
    }

    @RequestMapping(value = "/homepage/training/list")
    @ResponseBody
    public List<TrainingEntity> trainingAllList(TrainingEntity trainingEntity){
        return trainingManagementService.trainingAllList(trainingEntity);
    }

    @RequestMapping(value = "/homepage/training/details/{id}")
    @ResponseBody
    public ModelAndView trainingDetails(@PathVariable("id") Long id){
        final TrainingEntity trainingEntity = trainingManagementService.trainingDetails(id);
        final Map<String, Object> map = new HashMap<>(3);
        map.put("title",trainingEntity.getTrainingTitle());
        map.put("details",trainingEntity.getTrainingDetails());
        map.put("createDate",trainingEntity.getCreateDate());
        return new ModelAndView("pc/zyxueyuan/xbpxdetails",map);
    }

    /**
     * 校本培训新增
     * @param trainingEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = TRAINING_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO trainingAdd(@RequestBody TrainingEntity trainingEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校本培训新增,title:{}",trainingEntity.getTrainingTitle());
            }
            trainingEntity.setCreateUserId(getLoginId());
            final Boolean flag = trainingManagementService.trainingAdd(trainingEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 校本培训修改
     * @param trainingEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = TRAINING_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO trainingEdit(@RequestBody TrainingEntity trainingEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校本培训修改,title:{}",trainingEntity.getTrainingTitle());
            }
            trainingEntity.setUpdateUserId(getLoginId());
            final Boolean flag = trainingManagementService.trainingEdit(trainingEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 校本培训删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = TRAINING_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO trainingDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("校本培训删除,id:{}",id);
            }
            final Boolean flag = trainingManagementService.trainingDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
