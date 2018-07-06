package domain.home.service;

import domain.home.entity.CivilizationEntity;
import domain.shiro.entity.PageQueryResult;

public interface CivilizationManagementService {
    /**
     * 文明创建分页
     * @param civilizationEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult civilizationList(CivilizationEntity civilizationEntity);

    Boolean civilizationAdd(CivilizationEntity civilizationEntity);
}
