package domain.home.service.impl;

import domain.home.dao.CivilizationDao;
import domain.home.dao.SearchDao;
import domain.home.entity.CivilizationEntity;
import domain.home.service.CivilizationManagementService;
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
public class CivilizationManagementServiceImpl implements CivilizationManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CivilizationManagementServiceImpl.class);

    final private CivilizationDao civilizationDao;
    final private SearchDao searchDao;

    @Autowired
    public CivilizationManagementServiceImpl(CivilizationDao civilizationDao,SearchDao searchDao){
        this.civilizationDao = civilizationDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult civilizationList(CivilizationEntity civilizationEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = civilizationDao.civilizationCount(civilizationEntity);

        List<CivilizationEntity> civilizationEntities = newArrayList();

        if (count > 0){
            civilizationEntities = civilizationDao.civilizationList(civilizationEntity);
        }

        pageQueryResult.setRows(civilizationEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean civilizationAdd(CivilizationEntity civilizationEntity) {
        final Boolean flag = civilizationDao.civilizationAdd(civilizationEntity) > 0;

        if (flag){
            searchDao.searchAdd(civilizationEntity.getId(),civilizationEntity.getCivilizationTitle(),"/homepage/civilization/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("文明创建新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean civilizationEdit(CivilizationEntity civilizationEntity) {
        final Boolean flag = civilizationDao.civilizationEdit(civilizationEntity) > 0;
        if (flag){
            searchDao.searchEdit(civilizationEntity.getId(),civilizationEntity.getCivilizationTitle(),"/homepage/civilization/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("文明创建修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean civilizationDelete(Long id, Long loginId) {
        final Boolean flag = civilizationDao.civilizationDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("文明创建删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<CivilizationEntity> civilizationAllList(CivilizationEntity civilizationEntity) {
        return civilizationDao.civilizationAllList(civilizationEntity);
    }

    @Override
    public CivilizationEntity civilizationDetails(Long id) {
        return civilizationDao.civilizationDetails(id);
    }
}
