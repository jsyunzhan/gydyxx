package domain.home.service.impl;

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

    @Autowired
    public TeacherManagementServiceImpl(TeacherDao teacherDao){
        this.teacherDao = teacherDao;
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
}
