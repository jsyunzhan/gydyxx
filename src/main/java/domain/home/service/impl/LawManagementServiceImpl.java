package domain.home.service.impl;

import domain.home.dao.LawDao;
import domain.home.entity.LawEntity;
import domain.home.service.LawManagementService;
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
public class LawManagementServiceImpl implements LawManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(LawManagementServiceImpl.class);

    final private LawDao lawDao;

    @Autowired
    public LawManagementServiceImpl(LawDao lawDao){
        this.lawDao=lawDao;
    }

    @Override
    public PageQueryResult lawList(LawEntity lawEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<LawEntity> lawEntities = newArrayList();

        final Integer count = lawDao.lawCount(lawEntity);

        if (count > 0){
            lawEntities = lawDao.lawList(lawEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(lawEntities);
        return pageQueryResult;
    }
}
