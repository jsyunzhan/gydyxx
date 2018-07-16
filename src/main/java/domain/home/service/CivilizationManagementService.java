package domain.home.service;

import domain.home.entity.CivilizationEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface CivilizationManagementService {
    /**
     * 文明创建分页
     * @param civilizationEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult civilizationList(CivilizationEntity civilizationEntity);

    /**
     * 文明创建新增
     * @param civilizationEntity 新增实体
     * @return Boolean
     */
    Boolean civilizationAdd(CivilizationEntity civilizationEntity);

    /**
     * 文明创建修改
     * @param civilizationEntity 修改实体
     * @return Boolean
     */
    Boolean civilizationEdit(CivilizationEntity civilizationEntity);

    /**
     * 文明创建删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean civilizationDelete(Long id, Long loginId);

    /**
     * 文明创建接口
     * @param civilizationEntity 查询实体
     * @return List<CivilizationEntity>
     */
    List<CivilizationEntity> civilizationAllList(CivilizationEntity civilizationEntity);
}
