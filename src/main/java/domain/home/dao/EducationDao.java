package domain.home.dao;

import domain.home.entity.EducationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationDao {
    Integer educationCount(EducationEntity educationEntity);

    List<EducationEntity> educationList(EducationEntity educationEntity);

    Integer educationAdd(EducationEntity educationEntity);

    Integer educationEdit(EducationEntity educationEntity);

    Integer educationDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
