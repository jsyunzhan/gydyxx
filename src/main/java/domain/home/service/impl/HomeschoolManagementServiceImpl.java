package domain.home.service.impl;

import domain.home.dao.HomeschoolDao;
import domain.home.dao.SearchDao;
import domain.home.entity.HomeschoolEntity;
import domain.home.service.HomeschoolManagementService;
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
public class HomeschoolManagementServiceImpl implements HomeschoolManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeschoolManagementServiceImpl.class);

    final private HomeschoolDao homeschoolDao;
    final private SearchDao searchDao;

    @Autowired
    public HomeschoolManagementServiceImpl(HomeschoolDao homeschoolDao,SearchDao searchDao){
        this.homeschoolDao = homeschoolDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult homeschoolList(HomeschoolEntity homeschoolEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<HomeschoolEntity> homeschoolEntities = newArrayList();

        final Integer count = homeschoolDao.homeschoolCount(homeschoolEntity);

        if (count > 0){
            homeschoolEntities = homeschoolDao.homeschoolList(homeschoolEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(homeschoolEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean homeschoolAdd(HomeschoolEntity homeschoolEntity) {
        final Boolean flag = homeschoolDao.homeschoolAdd(homeschoolEntity) > 0;

        if (flag){
            searchDao.searchAdd(homeschoolEntity.getId(),homeschoolEntity.getHomeschoolTitle(),"/homepage/homeschool/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校心桥新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean homeschoolEdit(HomeschoolEntity homeschoolEntity) {
        final Boolean flag = homeschoolDao.homeschoolEdit(homeschoolEntity) > 0;
        if (flag){
            searchDao.searchEdit(homeschoolEntity.getId(),homeschoolEntity.getHomeschoolTitle(),"/homepage/homeschool/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校心桥修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean homeschoolDelete(Long id, Long loginId) {
        final Boolean flag = homeschoolDao.homeschoolDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/homeschool/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("家校心桥删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<HomeschoolEntity> homeschoolAllList(HomeschoolEntity homeschoolEntity) {
        return homeschoolDao.homeschoolAllList(homeschoolEntity);
    }

    @Override
    public HomeschoolEntity homeDetails(Long id) {
        return homeschoolDao.homeDetails(id);
    }
}
