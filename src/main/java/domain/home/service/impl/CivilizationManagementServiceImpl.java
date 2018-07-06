package domain.home.service.impl;

import domain.home.dao.CivilizationDao;
import domain.home.entity.CivilizationEntity;
import domain.home.service.CivilizationManagementService;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Transactional
public class CivilizationManagementServiceImpl implements CivilizationManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CivilizationManagementServiceImpl.class);

    final private CivilizationDao civilizationDao;

    @Autowired
    public CivilizationManagementServiceImpl(CivilizationDao civilizationDao){
        this.civilizationDao = civilizationDao;
    }

    @Override
    public PageQueryResult civilizationList(CivilizationEntity civilizationEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = civilizationDao.civilizationCount(civilizationEntity);

        List<CivilizationEntity> civilizationEntities = newArrayList();

        if (count > 0){
            civilizationEntities = civilizationDao.civilizationList(civilizationEntity);
        }

        pageQueryResult.setRows(civilizationEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean civilizationAdd(CivilizationEntity civilizationEntity) {
        final Boolean flag = civilizationDao.civilizationAdd(civilizationEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("文明创建新增结果:",flag);
        }
        return flag;
    }
}
