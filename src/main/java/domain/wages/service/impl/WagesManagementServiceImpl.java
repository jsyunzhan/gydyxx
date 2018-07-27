package domain.wages.service.impl;

import domain.shiro.dao.AccountDao;
import domain.shiro.entity.AccountEntity;
import domain.shiro.entity.PageQueryResult;
import domain.wages.dao.WagesDao;
import domain.wages.entity.RequsetVOEntity;
import domain.wages.entity.WagesEntity;
import domain.wages.entity.WagesMainEntity;
import domain.wages.service.WagesManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Transactional
public class WagesManagementServiceImpl implements WagesManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WagesManagementServiceImpl.class);

    final private WagesDao wagesDao;
    final private AccountDao accountDao;

    @Autowired
    public WagesManagementServiceImpl(WagesDao wagesDao,AccountDao accountDao){
        this.wagesDao = wagesDao;
        this.accountDao = accountDao;
    }

    @Override
    public PageQueryResult wagesMainList(WagesMainEntity wagesMainEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = wagesDao.wagesMainCount(wagesMainEntity);

        List<WagesMainEntity> wagesMainEntities = newArrayList();

        if (count > 0){
            wagesMainEntities = wagesDao.wagesMainList(wagesMainEntity);
        }

        pageQueryResult.setRows(wagesMainEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public List<AccountEntity> accountAllList() {
        return accountDao.accountAllList();
    }

    @Override
    public Boolean wagesAdd(RequsetVOEntity requsetVOEntity, Long loginId) {
        final List<WagesEntity> wagesEntities = requsetVOEntity.getWagesEntities();
        final WagesMainEntity wagesMainEntity = requsetVOEntity.getWagesMainEntity();
        wagesMainEntity.setCreateUserId(loginId);
        final Boolean flag = wagesDao.wagesAddMain(wagesMainEntity) > 0;

        if (flag){
            for (WagesEntity wagesEntity:
            wagesEntities) {
                wagesEntity.setWagesId(wagesMainEntity.getId());
                wagesEntity.setWagesData(wagesMainEntity.getWagesDate());
                wagesEntity.setCreateUserId(loginId);
                wagesDao.wagesAdd(wagesEntity);
            }
        }

        return flag;
    }

    @Override
    public List<WagesEntity> wagesDetails(Long id) {
        return wagesDao.wagesDetails(id);
    }
}
