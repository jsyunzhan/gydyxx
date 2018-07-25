package domain.home.service.impl;

import domain.home.dao.FarDao;
import domain.home.dao.SearchDao;
import domain.home.entity.FarEntity;
import domain.home.service.FarManagementService;
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
public class FarManagementServiceImpl implements FarManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(FarManagementServiceImpl.class);

    final private FarDao farDao;
    final private SearchDao searchDao;

    @Autowired
    public FarManagementServiceImpl(FarDao farDao,SearchDao searchDao){
        this.farDao = farDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult farList(FarEntity farEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<FarEntity> farEntities = newArrayList();

        final Integer count = farDao.farCount(farEntity);

        if (count > 0){
            farEntities = farDao.farList(farEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(farEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean farAdd(FarEntity farEntity) {
        final Boolean flag = farDao.farAdd(farEntity) > 0;

        if (flag){
            searchDao.searchAdd(farEntity.getId(),farEntity.getFarTitle(),"/homepage/far/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用工作室新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean farEdit(FarEntity farEntity) {
        final Boolean flag = farDao.farEdit(farEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用工作室修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean farDelete(Long id, Long loginId) {
        final Boolean flag = farDao.farDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用工作室删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<FarEntity> farAllList(FarEntity farEntity) {
        return farDao.farAllList(farEntity);
    }

    @Override
    public FarEntity farDetails(Long id) {
        return farDao.farDetails(id);
    }
}
