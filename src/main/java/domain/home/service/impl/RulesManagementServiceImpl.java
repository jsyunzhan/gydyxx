package domain.home.service.impl;

import domain.home.dao.RulesDao;
import domain.home.dao.SearchDao;
import domain.home.entity.RulesEntity;
import domain.home.service.RulesManagementService;
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
public class RulesManagementServiceImpl implements RulesManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(RulesManagementServiceImpl.class);

    final private RulesDao rulesDao;
    final private SearchDao searchDao;

    @Autowired
    public RulesManagementServiceImpl(RulesDao rulesDao,SearchDao searchDao){
        this.rulesDao = rulesDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult rulesList(RulesEntity rulesEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<RulesEntity> rulesEntities = newArrayList();

        final Integer count = rulesDao.rulesCount(rulesEntity);

        if (count > 0){
            rulesEntities = rulesDao.rulesList(rulesEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(rulesEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean rulesAdd(RulesEntity rulesEntity) {
        final Boolean flag = rulesDao.rulesAdd(rulesEntity) > 0;

        if (flag){
            searchDao.searchAdd(rulesEntity.getId(),rulesEntity.getRulesTitle(),"/homepage/rules/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("规章制度新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean rulesEdit(RulesEntity rulesEntity) {
        final Boolean flag = rulesDao.rulesEdit(rulesEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("规章制度修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean rulesDelete(Long id, Long loginId) {
        final Boolean flag = rulesDao.rulesDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("规章制度删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<RulesEntity> rulesAllList(RulesEntity rulesEntity) {
        return rulesDao.rulesAllList(rulesEntity);
    }

    @Override
    public RulesEntity rulesDetails(Long id) {
        return rulesDao.rulesDetails(id);
    }
}
