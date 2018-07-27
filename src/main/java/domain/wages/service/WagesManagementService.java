package domain.wages.service;

import domain.shiro.entity.AccountEntity;
import domain.shiro.entity.PageQueryResult;
import domain.wages.entity.RequsetVOEntity;
import domain.wages.entity.WagesEntity;
import domain.wages.entity.WagesMainEntity;

import java.util.List;

public interface WagesManagementService {
    /**
     * 工资页分页
     * @param wagesMainEntity 查询实体
     * @return PageQueryResult
     */
    PageQueryResult wagesMainList(WagesMainEntity wagesMainEntity);

    /**
     * 用户表数据
     * @return List<AccountEntity>
     */
    List<AccountEntity> accountAllList();

    /**
     * 工资新增
     * @param requsetVOEntity 新增实体
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean wagesAdd(RequsetVOEntity requsetVOEntity, Long loginId);

    /**
     * 工资详情
     * @param id
     * @return
     */
    List<WagesEntity> wagesDetails(Long id);

    /**
     * 工资删除
     * @param id id
     * @return Boolean
     */
    Boolean wagesDelete(Long id);

    /**
     * 工资修改
     * @param requsetVOEntity 修改实体
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean wagesEdit(RequsetVOEntity requsetVOEntity, Long loginId);
}
