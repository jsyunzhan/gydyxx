package domain.home.service.impl;

import domain.home.dao.ClassroomDao;
import domain.home.dao.SearchDao;
import domain.home.entity.ClassroomEntity;
import domain.home.service.ClassroomManagementService;
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
public class ClassroomManagementServiceImpl implements ClassroomManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomManagementServiceImpl.class);

    final private ClassroomDao classroomDao;
    final private SearchDao searchDao;

    @Autowired
    public ClassroomManagementServiceImpl(ClassroomDao classroomDao,SearchDao searchDao){
        this.classroomDao = classroomDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult classroomList(ClassroomEntity classroomEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = classroomDao.classroomCount(classroomEntity);

        List<ClassroomEntity> classroomEntities = newArrayList();

        if (count > 0){
            classroomEntities = classroomDao.classroomList(classroomEntity);
        }

        pageQueryResult.setRows(classroomEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean classroomAdd(ClassroomEntity classroomEntity) {
        final Boolean flag = classroomDao.classroomAdd(classroomEntity) > 0;

        if (flag){
            searchDao.searchAdd(classroomEntity.getId(),classroomEntity.getClassroomTitle(),"t_home_classroom");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用课堂新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean classroomEdit(ClassroomEntity classroomEntity) {
        final Boolean flag = classroomDao.classroomEdit(classroomEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用课堂修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean classroomDelete(Long id, Long loginId) {
        final Boolean flag = classroomDao.classroomDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用课堂删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<ClassroomEntity> classroomAllList(ClassroomEntity classroomEntity) {
        return classroomDao.classroomAllList(classroomEntity);
    }

    @Override
    public ClassroomEntity classroomDetails(Long id) {
        return classroomDao.classroomDetails(id);
    }
}
