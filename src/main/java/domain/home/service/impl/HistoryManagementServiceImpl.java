package domain.home.service.impl;

import domain.home.dao.HistoryDao;
import domain.home.entity.HistoryEntity;
import domain.home.service.HistoryManagementService;
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
public class HistoryManagementServiceImpl implements HistoryManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryManagementServiceImpl.class);

    final private HistoryDao historyDao;

    @Autowired
    public HistoryManagementServiceImpl(HistoryDao historyDao){
        this.historyDao = historyDao;
    }

    @Override
    public PageQueryResult historyList(HistoryEntity historyEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = historyDao.historyCount(historyEntity);

        List<HistoryEntity> historyEntities = newArrayList();

        if (count > 0){
            historyEntities = historyDao.historyList(historyEntity);
        }

        pageQueryResult.setRows(historyEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean historyAdd(HistoryEntity historyEntity) {
        final Boolean flag = historyDao.historyAdd(historyEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校史天地新增:",flag);
        }
        return flag;
    }

    @Override
    public Boolean historyEdit(HistoryEntity historyEntity) {
        final Boolean flag = historyDao.historyEdit(historyEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校史天地修改:",flag);
        }
        return flag;
    }

    @Override
    public Boolean historyDelete(Long id, Long loginId) {
        final Boolean flag = historyDao.historyDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校史天地修改:",flag);
        }
        return flag;
    }

    @Override
    public List<HistoryEntity> historyAllList(HistoryEntity historyEntity) {
        return historyDao.historyAllList(historyEntity);
    }

    @Override
    public HistoryEntity historyDetails(Long id) {
        return historyDao.historyDetails(id);
    }
}
