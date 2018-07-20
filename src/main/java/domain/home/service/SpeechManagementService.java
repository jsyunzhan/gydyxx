package domain.home.service;

import domain.home.entity.SpeechEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface SpeechManagementService {
    /**
     * 国旗下讲话分页
     * @param speechEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult speechList(SpeechEntity speechEntity);

    /**
     * 国旗下讲话新增
     * @param speechEntity 新增实体
     * @return Boolean
     */
    Boolean speechAdd(SpeechEntity speechEntity);

    /**
     * 国旗下讲话修改
     * @param speechEntity 修改实体
     * @return Boolean
     */
    Boolean speechEdit(SpeechEntity speechEntity);

    /**
     * 国旗下讲话删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean speechDelete(Long id, Long loginId);

    List<SpeechEntity> speechAllList(SpeechEntity speechEntity);

    SpeechEntity speechDetails(Long id);
}
