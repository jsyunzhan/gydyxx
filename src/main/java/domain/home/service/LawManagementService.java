package domain.home.service;

import domain.home.entity.LawEntity;
import domain.shiro.entity.PageQueryResult;

public interface LawManagementService {
    /**
     * 法制校园分页
     * @param lawEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult lawList(LawEntity lawEntity);
}
