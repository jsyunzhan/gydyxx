package domain.home.service;

import domain.home.entity.FarEntity;
import domain.shiro.entity.PageQueryResult;

public interface FarManagementService {
    /**
     * 致远工作室分页
     * @param farEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult farList(FarEntity farEntity);

    /**
     * 致用工作室新增
     * @param farEntity 新增实体
     * @return Boolean
     */
    Boolean farAdd(FarEntity farEntity);

    /**
     * 致远工作室修改
     * @param farEntity 修改实体
     * @return Boolean
     */
    Boolean farEdit(FarEntity farEntity);

    /**
     * 致远工作室删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean farDelete(Long id, Long loginId);
}
