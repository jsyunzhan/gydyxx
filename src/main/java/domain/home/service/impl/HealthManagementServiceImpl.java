package domain.home.service.impl;

import domain.home.dao.HealthDao;
import domain.home.dao.SearchDao;
import domain.home.entity.HealthEntity;
import domain.home.service.HealthManagementService;
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
public class HealthManagementServiceImpl implements HealthManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthManagementServiceImpl.class);

    final private HealthDao healthDao;
    final private SearchDao searchDao;

    @Autowired
    public HealthManagementServiceImpl(HealthDao healthDao,SearchDao searchDao){
        this.healthDao = healthDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult healthList(HealthEntity healthEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<HealthEntity> healthEntities = newArrayList();

        final Integer count = healthDao.healthCount(healthEntity);

        if (count > 0){
            healthEntities = healthDao.healthList(healthEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(healthEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean healthAdd(HealthEntity healthEntity) {
        final Boolean flag = healthDao.healthAdd(healthEntity) > 0;

        if (flag){
            searchDao.searchAdd(healthEntity.getId(),healthEntity.getHealthTitle(),"/homepage/health/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("健康教育新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean healthEdit(HealthEntity healthEntity) {
        final Boolean flag = healthDao.healthEdit(healthEntity) > 0;
        if (flag){
            searchDao.searchEdit(healthEntity.getId(),healthEntity.getHealthTitle(),"/homepage/health/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("健康教育修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean healthDelete(Long id, Long loginId) {
        final Boolean flag = healthDao.healthDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/health/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("健康教育删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<HealthEntity> healthAllList(HealthEntity healthEntity) {
        return healthDao.healthAllList(healthEntity);
    }

    @Override
    public HealthEntity healthDetails(Long id) {
        return healthDao.healthDetails(id);
    }
}
