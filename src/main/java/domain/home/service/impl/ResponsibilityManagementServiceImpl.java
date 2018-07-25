package domain.home.service.impl;

import domain.home.dao.ResponsibilityDao;
import domain.home.dao.SearchDao;
import domain.home.entity.ResponsibilityEntity;
import domain.home.service.ResponsibilityManagementService;
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
public class ResponsibilityManagementServiceImpl implements ResponsibilityManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponsibilityManagementServiceImpl.class);

    final private ResponsibilityDao responsibilityDao;
    final private SearchDao searchDao;

    @Autowired
    public ResponsibilityManagementServiceImpl(ResponsibilityDao responsibilityDao,SearchDao searchDao){
        this.responsibilityDao = responsibilityDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult responsibilityList(ResponsibilityEntity responsibilityEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<ResponsibilityEntity> responsibilityEntities = newArrayList();

        final Integer count = responsibilityDao.responsibilityCount(responsibilityEntity);

        if (count > 0){
            responsibilityEntities = responsibilityDao.responsibilityList(responsibilityEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(responsibilityEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean responsibilityAdd(ResponsibilityEntity responsibilityEntity) {
        final Boolean flag = responsibilityDao.responsibilityAdd(responsibilityEntity) > 0;

        if (flag){
            searchDao.searchAdd(responsibilityEntity.getId(),responsibilityEntity.getResponsibilityTitle(),"/homepage/responsibility/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("责任督学新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean responsibilityEdit(ResponsibilityEntity responsibilityEntity) {
        final Boolean flag = responsibilityDao.responsibilityEdit(responsibilityEntity) > 0;
        if (flag){
            searchDao.searchEdit(responsibilityEntity.getId(),responsibilityEntity.getResponsibilityTitle(),"/homepage/responsibility/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("责任督学修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean responsibilityDelete(Long id, Long loginId) {
        final Boolean flag = responsibilityDao.responsibilityDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("责任督学删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<ResponsibilityEntity> responsibilityAllList(ResponsibilityEntity responsibilityEntity) {
        return responsibilityDao.responsibilityAllList(responsibilityEntity);
    }

    @Override
    public ResponsibilityEntity responsibilityDetails(Long id) {
        return responsibilityDao.responsibilityDetails(id);
    }
}
