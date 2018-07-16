package domain.home.service;

import domain.home.entity.WorksEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

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

    /**
     * 作品管理修改
     * @param worksEntity 修改实体
     * @return Boolean
     */
    Boolean worksEdit(WorksEntity worksEntity);

    /**
     * 作品管理删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean worksDelete(Long id, Long loginId);

    /**
     * 作品管理接口
     * @param worksEntity 查询实体
     * @return List<WorksEntity>
     */
    List<WorksEntity> worksAllList(WorksEntity worksEntity);
}
