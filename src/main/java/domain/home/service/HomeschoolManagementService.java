package domain.home.service;

import domain.home.entity.HomeschoolEntity;
import domain.shiro.entity.PageQueryResult;

public interface HomeschoolManagementService {
    /**
     * 家校心桥分页
     * @param homeschoolEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult homeschoolList(HomeschoolEntity homeschoolEntity);

    /**
     * 家校心桥新增
     * @param homeschoolEntity 新增实体
     * @return Boolean
     */
    Boolean homeschoolAdd(HomeschoolEntity homeschoolEntity);

    /**
     * 家校心桥修改
     * @param homeschoolEntity 修改实体
     * @return Boolean
     */
    Boolean homeschoolEdit(HomeschoolEntity homeschoolEntity);

    /**
     * 家校心桥删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean homeschoolDelete(Long id, Long loginId);
}
