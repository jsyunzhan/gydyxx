package domain.home.dao;

import domain.home.entity.TeachingEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingDao {

    Integer teachingCount(TeachingEntity teachingEntity);

    List<TeachingEntity> teachingList(TeachingEntity teachingEntity);

    Integer teachingAdd(TeachingEntity teachingEntity);

    Integer teachingEdit(TeachingEntity teachingEntity);

    Integer teachingDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<TeachingEntity> teachingAllList(TeachingEntity teachingEntity);
}
