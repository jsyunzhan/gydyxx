package domain.home.service.impl;

import domain.home.dao.ResourcesDao;
import domain.home.entity.ResourcesEntity;
import domain.home.service.ResourcesManagementService;
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
public class ResourcesManagementServiceImpl implements ResourcesManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesManagementServiceImpl.class);

    final private ResourcesDao resourcesDao;

    @Autowired
    public ResourcesManagementServiceImpl(ResourcesDao resourcesDao){
        this.resourcesDao = resourcesDao;
    }

    @Override
    public PageQueryResult resourcesList(ResourcesEntity resourcesEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<ResourcesEntity> resourcesEntities = newArrayList();

        final Integer count = resourcesDao.resourcesCount(resourcesEntity);

        if (count > 0){
            resourcesEntities = resourcesDao.resourcesList(resourcesEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(resourcesEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean resourcesAdd(ResourcesEntity resourcesEntity) {
        final Boolean flag = resourcesDao.resourcesAdd(resourcesEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校资源新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean resourcesEdit(ResourcesEntity resourcesEntity) {
        final Boolean flag = resourcesDao.resourcesEdit(resourcesEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校资源修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean resourcesDelete(Long id, Long loginId) {
        final Boolean flag = resourcesDao.resourcesDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校资源删除结果:",flag);
        }
        return flag;
    }
}
