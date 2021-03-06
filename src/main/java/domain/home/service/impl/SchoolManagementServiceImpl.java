package domain.home.service.impl;

import domain.home.dao.SchoolDao;
import domain.home.dao.SearchDao;
import domain.home.entity.SchoolEntity;
import domain.home.service.SchoolManagementService;
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
public class SchoolManagementServiceImpl implements SchoolManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolManagementServiceImpl.class);

    final private SchoolDao schoolDao;
    final private SearchDao searchDao;

    @Autowired
    public SchoolManagementServiceImpl(SchoolDao schoolDao,SearchDao searchDao){
        this.schoolDao = schoolDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult schoolList(SchoolEntity schoolEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<SchoolEntity> schoolEntities = newArrayList();

        final Integer count = schoolDao.schoolCount(schoolEntity);

        if (count > 0){
            schoolEntities = schoolDao.schoolList(schoolEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(schoolEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean schoolAdd(SchoolEntity schoolEntity) {
        final Boolean flag = schoolDao.schoolAdd(schoolEntity) > 0;

        if (flag){
            searchDao.searchAdd(schoolEntity.getId(),schoolEntity.getSchoolTitle(),"/homepage/school/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学校风采新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean schoolEdit(SchoolEntity schoolEntity) {
        final Boolean flag = schoolDao.schoolEdit(schoolEntity) > 0;
        if (flag){
            searchDao.searchEdit(schoolEntity.getId(),schoolEntity.getSchoolTitle(),"/homepage/school/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学校风采修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean schoolDelete(Long id, Long loginId) {
        final Boolean flag = schoolDao.schoolDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/school/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学校风采删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<SchoolEntity> schoolAllList(SchoolEntity schoolEntity) {
        return schoolDao.schoolAllList(schoolEntity);
    }

    @Override
    public SchoolEntity schoolDetails(Long id) {
        return schoolDao.schoolDetails(id);
    }
}
