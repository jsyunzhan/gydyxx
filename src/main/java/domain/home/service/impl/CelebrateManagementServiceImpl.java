package domain.home.service.impl;

import domain.home.dao.CelebrateDao;
import domain.home.entity.CelebrateEntity;
import domain.home.service.CelebrateManagementService;
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
public class CelebrateManagementServiceImpl implements CelebrateManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CelebrateManagementServiceImpl.class);

    final private CelebrateDao celebrateDao;

    @Autowired
    public CelebrateManagementServiceImpl(CelebrateDao celebrateDao){
        this.celebrateDao = celebrateDao;
    }

    @Override
    public PageQueryResult celebrateList(CelebrateEntity celebrateEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = celebrateDao.celebrateCount(celebrateEntity);

        List<CelebrateEntity> celebrateEntities = newArrayList();

        if (count > 0){
            celebrateEntities = celebrateDao.celebrateInfo(celebrateEntity);
        }

        pageQueryResult.setRows(celebrateEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean celebrateAdd(CelebrateEntity celebrateEntity) {
        final Boolean flag = celebrateDao.celebrateAdd(celebrateEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校园节庆新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean celebrateEdit(CelebrateEntity celebrateEntity) {
        final Boolean flag = celebrateDao.celebrateEdit(celebrateEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校园节庆修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean celebrateDelete(Long id, Long loginId) {
        final Boolean flag = celebrateDao.celebrateDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校园节庆删除结果:",flag);
        }
        return flag;
    }
}
