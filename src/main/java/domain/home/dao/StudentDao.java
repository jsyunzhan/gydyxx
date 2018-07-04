package domain.home.dao;

import domain.home.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao {

    /**
     * 分页个数
     * @param studentEntity 分页实体
     * @return Integer
     */
    Integer studentCount(StudentEntity studentEntity);

    /**
     * 分页
     * @param studentEntity 分页实体
     * @return List<StudentEntity>
     */
    List<StudentEntity> studentList(StudentEntity studentEntity);

    /**
     * 学子风采新增
     * @param studentEntity 新增实体
     * @return Integer
     */
    Integer studentAdd(StudentEntity studentEntity);
}
