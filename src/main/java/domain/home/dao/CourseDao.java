package domain.home.dao;

import domain.home.entity.CourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao {

    Integer courseCount(CourseEntity courseEntity);

    List<CourseEntity> courseListInfo(CourseEntity courseEntity);

    Integer courseAdd(CourseEntity courseEntity);

    Integer courseEdit(CourseEntity courseEntity);

    Integer courseDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<CourseEntity> courseAllList(CourseEntity courseEntity);
}
