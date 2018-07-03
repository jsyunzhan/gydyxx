package domain.home.dao;

import domain.home.entity.TeacherEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {
    /**
     * 名师风采分页个数
     * @param teacherEntity 分页实体
     * @return Integer
     */
    Integer teacherCount(TeacherEntity teacherEntity);

    /**
     * 名师风采分页
     * @param teacherEntity 分页实体
     * @return List<TeacherEntity>
     */
    List<TeacherEntity> teacherList(TeacherEntity teacherEntity);

    /**
     * 名师风采新增
     * @param teacherEntity 新增实体
     * @return Integer
     */
    Integer teacherAdd(TeacherEntity teacherEntity);
}
