package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.dao.TeachingDao;
import domain.home.entity.TeachingEntity;
import domain.home.service.TeachingManagementService;
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
public class TeachingManagementServiceImpl implements TeachingManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TeachingManagementServiceImpl.class);

    final private TeachingDao teachingDao;
    final private SearchDao searchDao;

    @Autowired
    public TeachingManagementServiceImpl(TeachingDao teachingDao,SearchDao searchDao){
        this.teachingDao = teachingDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult teachingList(TeachingEntity teachingEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<TeachingEntity> teachingEntities = newArrayList();

        final Integer count = teachingDao.teachingCount(teachingEntity);

        if (count > 0){
            teachingEntities = teachingDao.teachingList(teachingEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(teachingEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean teachingAdd(TeachingEntity teachingEntity) {
        final Boolean flag = teachingDao.teachingAdd(teachingEntity) > 0;

        if (flag){
            searchDao.searchAdd(teachingEntity.getId(),teachingEntity.getTeachingTitle(),"/homepage/teaching/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("教学资源新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean teachingEdit(TeachingEntity teachingEntity) {
        final Boolean flag = teachingDao.teachingEdit(teachingEntity) > 0;
        if (flag){
            searchDao.searchEdit(teachingEntity.getId(),teachingEntity.getTeachingTitle(),"/homepage/teaching/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("教学资源修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean teachingDelete(Long id, Long loginId) {
        final Boolean flag = teachingDao.teachingDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/teaching/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("教学资源删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<TeachingEntity> teachingAllList(TeachingEntity teachingEntity) {
        return teachingDao.teachingAllList(teachingEntity);
    }

    @Override
    public TeachingEntity teacheringDetails(Long id) {
        return teachingDao.teacheringDetails(id);
    }
}
