package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.dao.TeacherDao;
import domain.home.entity.TeacherEntity;
import domain.home.service.TeacherManagementService;
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
public class TeacherManagementServiceImpl implements TeacherManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherManagementServiceImpl.class);

    final private TeacherDao teacherDao;
    final private SearchDao searchDao;

    @Autowired
    public TeacherManagementServiceImpl(TeacherDao teacherDao,SearchDao searchDao){
        this.teacherDao = teacherDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult teacherList(TeacherEntity teacherEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        List<TeacherEntity> teacherEntities = newArrayList();

        final Integer count = teacherDao.teacherCount(teacherEntity);

        if (count > 0){
            teacherEntities = teacherDao.teacherList(teacherEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(teacherEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean teacherAdd(TeacherEntity teacherEntity) {

        final Boolean flag = teacherDao.teacherAdd(teacherEntity) > 0;

        if (flag){
            searchDao.searchAdd(teacherEntity.getId(),teacherEntity.getTeacherTitle(),"/homepage/teacher/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("名师风采新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean teacherEdit(TeacherEntity teacherEntity) {
        final Boolean flag = teacherDao.teacherEdit(teacherEntity) > 0;
        if (flag){
            searchDao.searchEdit(teacherEntity.getId(),teacherEntity.getTeacherTitle(),"/homepage/teacher/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("名师风采修改结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean teacherDelete(Long id, Long loginId) {
        final Boolean flag = teacherDao.teacherDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("名师风采删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<TeacherEntity> teacherAllList(TeacherEntity teacherEntity) {
        return teacherDao.teacherAllList(teacherEntity);
    }

    @Override
    public TeacherEntity teacherDetails(Long id) {
        return teacherDao.teacherDetails(id);
    }
}
