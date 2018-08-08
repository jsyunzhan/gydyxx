package domain.home.service.impl;

import domain.home.dao.ResourcesDao;
import domain.home.dao.SearchDao;
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
    final private SearchDao searchDao;

    @Autowired
    public ResourcesManagementServiceImpl(ResourcesDao resourcesDao,SearchDao searchDao){
        this.resourcesDao = resourcesDao;
        this.searchDao = searchDao;
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

        if (flag){
            searchDao.searchAdd(resourcesEntity.getId(),resourcesEntity.getResourcesTitle(),"/homepage/resources/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校资源新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean resourcesEdit(ResourcesEntity resourcesEntity) {
        final Boolean flag = resourcesDao.resourcesEdit(resourcesEntity) > 0;
        if (flag){
            searchDao.searchEdit(resourcesEntity.getId(),resourcesEntity.getResourcesTitle(),"/homepage/resources/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校资源修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean resourcesDelete(Long id, Long loginId) {
        final Boolean flag = resourcesDao.resourcesDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/resources/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校资源删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<ResourcesEntity> resourcesAllList(ResourcesEntity resourcesEntity) {
        return resourcesDao.resourcesAllList(resourcesEntity);
    }

    @Override
    public ResourcesEntity resourcesDetails(Long id) {
        return resourcesDao.resourcesDetails(id);
    }
}
