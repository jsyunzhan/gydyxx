package domain.home.controller;

import domain.home.entity.LeaderEntity;
import domain.home.service.LeaderManagementService;
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

import static domain.home.HomeWebForward.TO_LEADER_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class LeaderManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderManagementController.class);

    final private LeaderManagementService leaderManagementService;

    @Autowired
    public LeaderManagementController(LeaderManagementService leaderManagementService){
        this.leaderManagementService = leaderManagementService;
    }

    /**
     * 去领导简介页面
     * @return ModelAndView
     */
    @RequestMapping(value = LEADER_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_LEADER_PAGE);
    }

    /**
     * 领导介绍分页
     * @param leaderEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = LEADER_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult leaderList(LeaderEntity leaderEntity){
        return leaderManagementService.leaderList(leaderEntity);
    }

    /**
     * 领导介绍接口
     * @param leaderEntity 查询实体
     * @return List<LeaderEntity>
     */
    @RequestMapping(value = "/homepage/leader/list")
    @ResponseBody
    public List<LeaderEntity> leaderAllList(LeaderEntity leaderEntity){
        return leaderManagementService.leaderAllList(leaderEntity);
    }

    /**
     * 领导简介新增
     * @param leaderEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = LEADER_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO leaderAdd(@RequestBody LeaderEntity leaderEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("领导简介新增,title:{}",leaderEntity.getLeaderTitle());
            }
            leaderEntity.setCreateUserId(getLoginId());
            final Boolean flag = leaderManagementService.leaderAdd(leaderEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 领导简介修改
     * @param leaderEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = LEADER_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO leaderEdit(@RequestBody LeaderEntity leaderEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("领导简介修改,title:{}",leaderEntity.getLeaderTitle());
            }
            leaderEntity.setUpdateUserId(getLoginId());
            final Boolean flag = leaderManagementService.leaderEdit(leaderEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;

    }

    /**
     * 领导简介删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = LEADER_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO leaderDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("领导简介删除,id:{}",id);
            }
            final Boolean flag = leaderManagementService.leaderDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
