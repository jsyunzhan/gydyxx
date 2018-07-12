package domain.home.controller;

import domain.home.entity.HomeschoolEntity;
import domain.home.service.HomeschoolManagementService;
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

import static domain.home.HomeWebForward.TO_HOMESCHOOL_PAGE;
import static domain.home.HomeWebURLMapping.*;

/**
 * 家校心桥
 */
@Controller
public class HomeschoolManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeschoolManagementController.class);

    final private HomeschoolManagementService homeschoolManagementService;

    @Autowired
    public HomeschoolManagementController(HomeschoolManagementService homeschoolManagementService){
        this.homeschoolManagementService = homeschoolManagementService;
    }

    /**
     * 去家校心桥页面
     * @return ModelAndView
     */
    @RequestMapping(value = HOMESCHOOL_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_HOMESCHOOL_PAGE);
    }

    /**
     * 家校心桥分页
     * @param homeschoolEntity 分页实体
     * @return PageQueryResult
     */
    @RequestMapping(value = HOMESCHOOL_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult homeschoolList(HomeschoolEntity homeschoolEntity){
        return homeschoolManagementService.homeschoolList(homeschoolEntity);
    }

    /**
     * 家校心桥新增
     * @param homeschoolEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = HOMESCHOOL_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO homeschoolAdd(@RequestBody HomeschoolEntity homeschoolEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        homeschoolEntity.setCreateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("家校心桥新增,title:{}",homeschoolEntity.getHomeschoolTitle());
            }
            Boolean flag =  homeschoolManagementService.homeschoolAdd(homeschoolEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 家校心桥修改
     * @param homeschoolEntity 修改实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = HOMESCHOOL_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO homeschoolEdit(@RequestBody HomeschoolEntity homeschoolEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        homeschoolEntity.setUpdateUserId(getLoginId());
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("家校心桥修改,title:{}",homeschoolEntity.getHomeschoolTitle());
            }
            Boolean flag =  homeschoolManagementService.homeschoolEdit(homeschoolEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }

    /**
     * 家校心桥删除
     * @param id id
     * @return JsonResponseVO
     */
    @RequestMapping(value = HOMESCHOOL_MANAGEMENT_DELETE)
    @ResponseBody
    public JsonResponseVO homeschoolDelete(@PathVariable("id") Long id){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("家校心桥删除,id:{}",id);
            }
            Boolean flag =  homeschoolManagementService.homeschoolDelete(id,getLoginId());

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }
        return jsonResponseVO;
    }
}
