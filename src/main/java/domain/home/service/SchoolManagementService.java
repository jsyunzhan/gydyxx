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

    /**
     * 学校风新增
     * @param schoolEntity 新增实体
     * @return Boolean
     */
    Boolean schoolAdd(SchoolEntity schoolEntity);

    /**
     * 学校风采修改
     * @param schoolEntity 修改实体
     * @return Boolean
     */
    Boolean schoolEdit(SchoolEntity schoolEntity);
}
