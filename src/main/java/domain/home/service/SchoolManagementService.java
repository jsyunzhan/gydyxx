package domain.home.service;

import domain.home.entity.SchoolEntity;
import domain.shiro.entity.PageQueryResult;

public interface SchoolManagementService {
    /**
     * 学校风采分页
     * @param schoolEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult schoolList(SchoolEntity schoolEntity);
}
