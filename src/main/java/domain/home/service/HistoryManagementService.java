package domain.home.service;

import domain.home.entity.HistoryEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface HistoryManagementService {
    /**
     * 校史天地分页
     * @param historyEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult historyList(HistoryEntity historyEntity);

    /**
     * 校史天地新增
     * @param historyEntity 新增实体
     * @return Boolean
     */
    Boolean historyAdd(HistoryEntity historyEntity);

    /**
     * 校史天地修改
     * @param historyEntity 修改实体
     * @return Boolean
     */
    Boolean historyEdit(HistoryEntity historyEntity);

    /**
     * 校史天地删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean historyDelete(Long id, Long loginId);

    List<HistoryEntity> historyAllList(HistoryEntity historyEntity);

    HistoryEntity historyDetails(Long id);
}
