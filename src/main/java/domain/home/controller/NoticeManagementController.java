package domain.home.controller;

import domain.home.entity.NoticeEntity;
import domain.home.service.NoticeManagementService;
import domain.shiro.controller.AbstractActionController;
import domain.shiro.entity.JsonResponseVO;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static domain.home.HomeWebForward.TO_NOTICE_PAGE;
import static domain.home.HomeWebURLMapping.*;

@Controller
public class NoticeManagementController extends AbstractActionController{
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeManagementController.class);

    final private NoticeManagementService noticeManagementService;

    @Autowired
    public NoticeManagementController(NoticeManagementService noticeManagementService){
        this.noticeManagementService = noticeManagementService;
    }

    /**
     * 去消息管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_PAGE)
    public ModelAndView index(){
        return new ModelAndView(TO_NOTICE_PAGE);
    }

    /**
     *  首页公告分页
     * @param noticeEntity noticeEntity
     * @return PageQueryResult
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_LIST)
    @ResponseBody
    public PageQueryResult noticeListInfo(NoticeEntity noticeEntity){
        return noticeManagementService.noticeListInfo(noticeEntity);
    }

    /**
     * 公告新增
     * @param noticeEntity 新增实体
     * @return JsonResponseVO
     */
    @RequestMapping(value = NOTICE_MANAGEMENT_ADD)
    @ResponseBody
    public JsonResponseVO noticeAdd(@RequestBody NoticeEntity noticeEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        noticeEntity.setCreateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("公告消息新增,title:{}",noticeEntity.getNoticeTitle());
            }
            Boolean flag =  noticeManagementService.noticeAdd(noticeEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }

    @RequestMapping(value = NOTICE_MANAGEMENT_EDIT)
    @ResponseBody
    public JsonResponseVO noticeEdit(@RequestBody NoticeEntity noticeEntity){
        final JsonResponseVO jsonResponseVO = new JsonResponseVO(Boolean.FALSE);
        noticeEntity.setUpdateUserId(getLoginId());

        try {
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("公告消息修改,title:{}",noticeEntity.getNoticeTitle());
            }
            Boolean flag =  noticeManagementService.noticeEdit(noticeEntity);

            jsonResponseVO.setSuccess(flag);
        }catch (Exception e){
            LOGGER.error("业务处理异常:",e);
        }

        return jsonResponseVO;
    }
}
