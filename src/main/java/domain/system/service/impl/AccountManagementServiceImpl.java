package domain.system.service.impl;

import domain.shiro.dao.AccountDao;
import domain.shiro.entity.AccountEntity;
import domain.shiro.entity.PageQueryResult;
import domain.system.dao.RoleDao;
import domain.system.entity.RoleEntity;
import domain.system.service.AccountManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Transactional
public class AccountManagementServiceImpl implements AccountManagementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagementServiceImpl.class);

    private final AccountDao accountDao;
    private final RoleDao roleDao;

    @Autowired
    public AccountManagementServiceImpl(AccountDao accountDao, RoleDao roleDao){
        this.accountDao = accountDao;
        this.roleDao = roleDao;
    }

    @Override
    public Boolean addAccount(AccountEntity accountEntity) {
        return accountDao.addAccout(accountEntity) > 0;
    }



    @Override
    public PageQueryResult accountListInof(AccountEntity accountEntity) {

        final PageQueryResult pageQueryResult = new PageQueryResult();
        final Integer count = accountDao.accountCount(accountEntity );
        List<AccountEntity> accountEntityList = newArrayList();
        if (count > 0){
            accountEntityList = accountDao.accountListInfo(accountEntity);
        }


        pageQueryResult.setRows(accountEntityList);
        pageQueryResult.setTotal(count);

        return pageQueryResult;
    }

    @Override
    public List<RoleEntity> getAllRole() {
        return roleDao.getAllRole();
    }

    @Override
    public Boolean accountAdd(AccountEntity accountEntity) {
        final Boolean flag = accountDao.addAccout(accountEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("用户新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean checkLoginName(Long id, String loginName) {
        return accountDao.checkLoginName(id,loginName) < 1;
    }

    @Override
    public Boolean accountEdit(AccountEntity accountEntity) {
        final Boolean flag = accountDao.editAccount(accountEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("用户修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean accountDelete(Long id, Long loginId) {

        final Boolean flag = accountDao.deleteAccount(id,loginId) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("用户删除结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean editPassWord(AccountEntity accountEntity) {

        final Boolean flag = accountDao.editPassWord(accountEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("用户修改密码结果:",flag);
        }

        return flag;
    }
}
