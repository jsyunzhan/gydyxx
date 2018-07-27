package domain.shiro.dao;

import domain.shiro.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    /**
     * 根据用户名获取用户信息
     * @param loginName 用户信息
     * @return AccountEntity
     */
    AccountEntity accoutInfo(String loginName);

    /**
     * 新增用户
     * @param accountEntity 用户实体
     * @return Integer
     */
    Integer addAccout(AccountEntity accountEntity);

    /**
     * 获取总数
     * @param accountEntity 查询实体
     * @return Integer
     */
    Integer accountCount(AccountEntity accountEntity);

    /**
     * 获取集合总数
     * @param accountEntity 查询实体
     * @return List<AccountEntity>
     */
    List<AccountEntity> accountListInfo(AccountEntity accountEntity);

    /**
     * 用户添加
     * @param accountEntity 添加实体
     * @return Integer
     */
    Integer accountAdd(AccountEntity accountEntity);

    /**
     * 根据id和 loginName获取个数-用于新增和修改的验证
     * @param id id
     * @param loginName 登录名
     * @return Integer
     */
    Integer checkLoginName(@Param("id") Long id, @Param("loginName") String loginName);

    /**
     * 用户修改
     * @param accountEntity 修改实体
     * @return Boolean
     */
    Integer editAccount(AccountEntity accountEntity);

    /**
     * 删除
     * @param id id
     * @param loginId 登录id
     * @return Boolean
     */
    Integer deleteAccount(@Param("id") Long id,@Param("updateUserId") Long loginId);

    /**
     * 用户修改密码
     * @param accountEntity 修改实体
     * @return Integer
     */
    Integer editPassWord(AccountEntity accountEntity);

    /**
     * 获取所有用户
     * @return List<AccountEntity>
     */
    List<AccountEntity> accountAllList();

    /**
     * 根据用户名搜索
     * @param userName
     * @return
     */
    AccountEntity checkUserName(String userName);
}
