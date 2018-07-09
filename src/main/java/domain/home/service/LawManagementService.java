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

    /**
     * 法制校园新增
     * @param lawEntity 新增实体
     * @return Boolean
     */
    Boolean lawAdd(LawEntity lawEntity);

    /**
     * 法制校园修改
     * @param lawEntity 修改实体
     * @return Boolean
     */
    Boolean lawEdit(LawEntity lawEntity);

    /**
     * 法制校园删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean lawDelete(Long id, Long loginId);
}
