package domain.home.service.impl;

import domain.home.dao.PartyDao;
import domain.home.entity.PartyEntity;
import domain.home.service.PartyManagementService;
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
public class PartyManagementServiceImpl implements PartyManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PartyManagementServiceImpl.class);

    final private PartyDao partyDao;

    @Autowired
    public PartyManagementServiceImpl(PartyDao partyDao){
        this.partyDao = partyDao;
    }

    @Override
    public PageQueryResult partyList(PartyEntity partyEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<PartyEntity> partyEntities = newArrayList();

        final Integer count = partyDao.partyCount(partyEntity);

        if (count > 0){
            partyEntities = partyDao.partyList(partyEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(partyEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean partyAdd(PartyEntity partyEntity) {
        final Boolean flag = partyDao.partyAdd(partyEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("党建工会新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean partyEdit(PartyEntity partyEntity) {
        final Boolean flag = partyDao.partyEdit(partyEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("党建工会修改结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean partyDelete(Long id, Long loginId) {
        final Boolean flag = partyDao.partyDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("党建工会删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<PartyEntity> partyAllList(PartyEntity partyEntity) {
        return partyDao.partyAllList(partyEntity);
    }

    @Override
    public PartyEntity partyDetails(Long id) {
        return partyDao.partyDetails(id);
    }
}
