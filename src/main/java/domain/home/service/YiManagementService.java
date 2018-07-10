package domain.home.service;

import domain.home.entity.YiEntity;
import domain.shiro.entity.PageQueryResult;

public interface YiManagementService {
    /**
     * 致用邑分页
     * @param yiEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult yiList(YiEntity yiEntity);

    /**
     * 致用邑新增
     * @param yiEntity 新增实体
     * @return Boolean
     */
    Boolean yiAdd(YiEntity yiEntity);

    /**
     * 致用邑修改
     * @param yiEntity 修改实体
     * @return Boolean
     */
    Boolean yiEdit(YiEntity yiEntity);

    /**
     * 致用邑删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean yiDelete(Long id, Long loginId);
}
