package domain.home.service.impl;

import domain.home.dao.SearchDao;
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
    final private SearchDao searchDao;

    @Autowired
    public StudentManagementServiceImpl(StudentDao studentDao,SearchDao searchDao){
        this.studentDao = studentDao;
        this.searchDao = searchDao;
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

        if (flag){
            searchDao.searchAdd(studentEntity.getId(),studentEntity.getStudentTitle(),"/homepage/student/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学子风采新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean studentEdit(StudentEntity studentEntity) {
        final Boolean flag = studentDao.studentEdit(studentEntity) > 0;
        if (flag){
            searchDao.searchEdit(studentEntity.getId(),studentEntity.getStudentTitle(),"/homepage/student/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学子风采修改结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean studentDelete(Long id, Long loginId) {
        final Boolean flag = studentDao.studentDelete(id,loginId) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学子风采删除结果:",flag);
        }

        return flag;
    }

    @Override
    public List<StudentEntity> studentAllList(StudentEntity studentEntity) {
        return studentDao.studentAllList(studentEntity);
    }

    @Override
    public StudentEntity studentDetails(Long id) {
        return studentDao.studentDetails(id);
    }
}
