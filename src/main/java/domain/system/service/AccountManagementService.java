package domain.system.service;

import domain.shiro.entity.AccountEntity;
import domain.shiro.entity.PageQueryResult;
import domain.system.entity.RoleEntity;

import java.util.List;

/**
 * 用户管理接口
 */
public interface AccountManagementService {

    /**
     * 添加用户
     * @param accountEntity 添加实体
     * @return Boolean
     */
    Boolean addAccount(AccountEntity accountEntity);


    /**
     *分页
     * @param  accountEntity
     * @return PageQueryResult
     */
    PageQueryResult accountListInof(AccountEntity accountEntity);

    /**
     * 获取所有角色，下拉框加载
     * @return List<RoleEntity>
     */
    List<RoleEntity> getAllRole();

    /**
     * 用户添加
     * @param accountEntity 添加实体
     * @return Boolean
     */
    Boolean accountAdd(AccountEntity accountEntity);

    /**
     * 新增和修改，验证登录名是否重
     * @param id id
     * @param loginName 登录名
     * @return Boolean
     */
    Boolean checkLoginName(Long id, String loginName);

    /**
     * 用户修改
     * @param accountEntity 修改实体
     * @return Boolean
     */
    Boolean accountEdit(AccountEntity accountEntity);

    /**
     * 删除
     * @param id id
     * @param loginId 登录id
     * @return Boolean
     */
    Boolean accountDelete(Long id, Long loginId);

    /**
     * 修改密码
     * @param accountEntity 修改实体
     * @return Boolean
     */
    Boolean editPassWord(AccountEntity accountEntity);
}
