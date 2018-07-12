package domain.home.dao;

import domain.home.entity.HealthEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthDao {
    Integer healthCount(HealthEntity healthEntity);

    List<HealthEntity> healthList(HealthEntity healthEntity);

    Integer healthAdd(HealthEntity healthEntity);

    Integer healthEdit(HealthEntity healthEntity);

    Integer healthDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
