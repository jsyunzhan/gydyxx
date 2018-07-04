package domain.home.service.impl;

import domain.home.dao.SubjectDao;
import domain.home.entity.SubjectEntity;
import domain.home.service.SubjectManagementService;
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
public class SubjectManagementServiceImpl implements SubjectManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectManagementServiceImpl.class);

    final private SubjectDao subjectDao;

    @Autowired
    public SubjectManagementServiceImpl(SubjectDao subjectDao){
        this.subjectDao = subjectDao;
    }

    @Override
    public PageQueryResult subjectList(SubjectEntity subjectEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<SubjectEntity> subjectEntities = newArrayList();

        final Integer count = subjectDao.subjectCount(subjectEntity);

        if (count > 0){
            subjectEntities = subjectDao.subjectList(subjectEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(subjectEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean subjectAdd(SubjectEntity subjectEntity) {
        final Boolean flag = subjectDao.subjectAdd(subjectEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("课题研究新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean subjectEdit(SubjectEntity subjectEntity) {
        final Boolean flag = subjectDao.subjectEdit(subjectEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("课题研究修改结果:",flag);
        }

        return flag;
    }
}
