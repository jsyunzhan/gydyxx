package domain.home.service.impl;

import domain.home.dao.StudentDao;
import domain.home.entity.StudentEntity;
import domain.home.service.StudentManagementService;
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
public class StudentManagementServiceImpl implements StudentManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentManagementServiceImpl.class);

    final private StudentDao studentDao;

    @Autowired
    public StudentManagementServiceImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public PageQueryResult studentList(StudentEntity studentEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<StudentEntity> studentEntities = newArrayList();

        final Integer count = studentDao.studentCount(studentEntity);

        if (count > 0){
            studentEntities = studentDao.studentList(studentEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(studentEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean studentAdd(StudentEntity studentEntity) {
        final Boolean flag = studentDao.studentAdd(studentEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学子风采新增结果:",flag);
        }

        return flag;
    }
}
