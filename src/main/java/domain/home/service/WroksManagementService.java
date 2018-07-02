package domain.home.service;

import domain.home.entity.WorksEntity;
import domain.shiro.entity.PageQueryResult;

public interface WroksManagementService {
    /**
     * 作品管理分页
     * @param worksEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult worksListInfo(WorksEntity worksEntity);

    /**
     * 作品管理新增
     * @param worksEntity 新增实体
     * @return Boolean
     */
    Boolean worksAdd(WorksEntity worksEntity);
}
