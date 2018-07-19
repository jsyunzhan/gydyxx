package domain.home.service;

import domain.home.entity.LeaderEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface LeaderManagementService {
    /**
     * 领导介绍分页
     * @param leaderEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult leaderList(LeaderEntity leaderEntity);

    /**
     * 领导简介新增
     * @param leaderEntity 新增实体
     * @return Boolean
     */
    Boolean leaderAdd(LeaderEntity leaderEntity);

    /**
     * 领导简介修改
     * @param leaderEntity 修改实体
     * @return Boolean
     */
    Boolean leaderEdit(LeaderEntity leaderEntity);

    /**
     * 领导简介删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean leaderDelete(Long id, Long loginId);

    /**
     * 领导简介接口
     * @param leaderEntity 查询实体
     * @return List<LeaderEntity>
     */
    List<LeaderEntity> leaderAllList(LeaderEntity leaderEntity);

    LeaderEntity leaderDetails(Long id);
}
