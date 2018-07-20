package domain.home.service.impl;

import domain.home.dao.EducationDao;
import domain.home.entity.EducationEntity;
import domain.home.service.EducationManagementService;
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
public class EducationManagementServiceImpl implements EducationManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(EducationManagementServiceImpl.class);

    final private EducationDao educationDao;

    @Autowired
    public EducationManagementServiceImpl(EducationDao educationDao){
        this.educationDao = educationDao;
    }

    @Override
    public PageQueryResult educationList(EducationEntity educationEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = educationDao.educationCount(educationEntity);

        List<EducationEntity> educationEntities = newArrayList();

        if (count > 0){
            educationEntities = educationDao.educationList(educationEntity);
        }

        pageQueryResult.setRows(educationEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean educationAdd(EducationEntity educationEntity) {
        final Boolean flag = educationDao.educationAdd(educationEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("教育科研新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean educationEdit(EducationEntity educationEntity) {
        final Boolean flag = educationDao.educationEdit(educationEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("教育科研新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean educationDelete(Long id, Long loginId) {
        final Boolean flag = educationDao.educationDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("教育科研删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<EducationEntity> educationAllList(EducationEntity educationEntity) {
        return educationDao.educationAllList(educationEntity);
    }

    @Override
    public EducationEntity educationDetails(Long id) {
        return educationDao.educationDetails(id);
    }
}
