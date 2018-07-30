package domain.home.controller;

import domain.home.entity.WindowEntity;
import domain.home.service.WindowManagementService;
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

import static domain.home.HomeWebForward.TO_WINDOW_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class WindowManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(WindowManagementController.class);

    final private WindowManagementService windowManagementService;

    @Autowired
    public WindowManagementController(WindowManagementService windowManagementService){
        this.windowManagementService = windowManagementService;
    }

    @RequestMapping(value = WINDOW_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_WINDOW_PAGE);
    }

    /**
     * 飘窗页面分页
     * @param windowEntity 查询实体
     * @return PageQueryResult
     */
    @RequestMapping(value = WINDOW_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult windowList(WindowEntity windowEntity){
        return windowManagementService.windowList(windowEntity);
    }

    @RequestMapping(value = "/homepage/window/list")
    @ResponseBody
    public List<WindowEntity> windowAllList(WindowEntity windowEntity){
        return windowManagementService.windowAllList(windowEntity);
    }

    /**
     * 飘窗页面新增
     * @param windowEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = WINDOW_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO windowAdd(@RequestBody WindowEntity windowEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("飘窗页面新增,title:{}",windowEntity.getWindowTitle());
            }
            windowEntity.setCreateUserId(getLoginId());
            final Boolean flag = windowManagementService.windowAdd(windowEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 飘窗修改
     * @param windowEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = WINDOW_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO windowEdit(@RequestBody WindowEntity windowEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("飘窗页面修改,title:{}",windowEntity.getWindowTitle());
            }
            windowEntity.setUpdateUserId(getLoginId());
            final Boolean flag = windowManagementService.windowEdit(windowEntity);
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 飘窗删除
     * @param id id
     * @return 当前登录id
     */
    @RequestMapping(value = WINDOW_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO windowDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("飘窗页面删除,id:{}",id);
            }
            final Boolean flag = windowManagementService.windowDelete(id,getLoginId());
            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
