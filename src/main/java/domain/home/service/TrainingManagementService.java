package domain.home.service;

import domain.home.entity.TrainingEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface TrainingManagementService {
    /**
     * 校本培训分页
     * @param trainingEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult trainingList(TrainingEntity trainingEntity);

    /**
     * 校本培训新增
     * @param trainingEntity 新增实体
     * @return Boolean
     */
    Boolean trainingAdd(TrainingEntity trainingEntity);

    /**
     * 校本培训修改
     * @param trainingEntity 修改实体
     * @return Boolean
     */
    Boolean trainingEdit(TrainingEntity trainingEntity);

    /**
     * 校本培训删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean trainingDelete(Long id, Long loginId);

    List<TrainingEntity> trainingAllList(TrainingEntity trainingEntity);
}
