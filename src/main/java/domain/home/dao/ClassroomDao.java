package domain.home.dao;

import domain.home.entity.ClassroomEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomDao {
    Integer classroomCount(ClassroomEntity classroomEntity);

    List<ClassroomEntity> classroomList(ClassroomEntity classroomEntity);

    Integer classroomAdd(ClassroomEntity classroomEntity);

    Integer classroomEdit(ClassroomEntity classroomEntity);

    Integer classroomDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
