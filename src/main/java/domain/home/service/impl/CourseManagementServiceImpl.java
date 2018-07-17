package domain.home.service.impl;

import domain.home.dao.CourseDao;
import domain.home.entity.CourseEntity;
import domain.home.service.CourseManagementService;
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
public class CourseManagementServiceImpl implements CourseManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseManagementServiceImpl.class);

    final private CourseDao courseDao;

    @Autowired
    public CourseManagementServiceImpl(CourseDao courseDao){
        this.courseDao = courseDao;
    }


    @Override
    public PageQueryResult courseList(CourseEntity courseEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = courseDao.courseCount(courseEntity);

        List<CourseEntity> courseEntities = newArrayList();

        if (count > 0){
            courseEntities = courseDao.courseListInfo(courseEntity);
        }

        pageQueryResult.setRows(courseEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean courseAdd(CourseEntity courseEntity) {
        final Boolean flag = courseDao.courseAdd(courseEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("班本课程新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean courseEdit(CourseEntity courseEntity) {
        final Boolean flag = courseDao.courseEdit(courseEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("班本课程修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean courseDelete(Long id, Long loginId) {
        final Boolean flag = courseDao.courseDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("班本课程删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<CourseEntity> courseAllList(CourseEntity courseEntity) {
        return courseDao.courseAllList(courseEntity);
    }
}
