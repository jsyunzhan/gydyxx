package domain.home.service.impl;

import domain.home.dao.LeaderDao;
import domain.home.dao.SearchDao;
import domain.home.entity.LeaderEntity;
import domain.home.service.LeaderManagementService;
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
public class LeaderManagementServiceImpl implements LeaderManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(LeaderManagementServiceImpl.class);

    final private LeaderDao leaderDao;
    final private SearchDao searchDao;

    @Autowired
    public LeaderManagementServiceImpl(LeaderDao leaderDao,SearchDao searchDao){
        this.leaderDao = leaderDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult leaderList(LeaderEntity leaderEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = leaderDao.newsCount(leaderEntity);

        List<LeaderEntity> leaderEntities = newArrayList();

        if (count > 0){
            leaderEntities = leaderDao.leaderList(leaderEntity);
        }

        pageQueryResult.setRows(leaderEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean leaderAdd(LeaderEntity leaderEntity) {
        final Boolean flag = leaderDao.leaderAdd(leaderEntity) > 0;

        if (flag){
            searchDao.searchAdd(leaderEntity.getId(),leaderEntity.getLeaderTitle(),"/homepage/leader/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("领导简介新增:",flag);
        }
        return flag;
    }

    @Override
    public Boolean leaderEdit(LeaderEntity leaderEntity) {
        final Boolean flag = leaderDao.leaderEdit(leaderEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("领导简介修改:",flag);
        }
        return flag;
    }

    @Override
    public Boolean leaderDelete(Long id, Long loginId) {
        final Boolean flag = leaderDao.leaderDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("领导简介删除:",flag);
        }
        return flag;
    }

    @Override
    public List<LeaderEntity> leaderAllList(LeaderEntity leaderEntity) {
        return leaderDao.leaderAllList(leaderEntity);
    }

    @Override
    public LeaderEntity leaderDetails(Long id) {
        return leaderDao.leaderDetails(id);
    }
}
