package domain.home.dao;

import domain.home.entity.ResponsibilityEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsibilityDao {
    Integer responsibilityCount(ResponsibilityEntity responsibilityEntity);

    List<ResponsibilityEntity> responsibilityList(ResponsibilityEntity responsibilityEntity);

    Integer responsibilityAdd(ResponsibilityEntity responsibilityEntity);

    Integer responsibilityEdit(ResponsibilityEntity responsibilityEntity);

    Integer responsibilityDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<ResponsibilityEntity> responsibilityAllList(ResponsibilityEntity responsibilityEntity);
}
