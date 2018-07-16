package domain.home.dao;

import domain.home.entity.FarEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarDao {
    Integer farCount(FarEntity farEntity);

    List<FarEntity> farList(FarEntity farEntity);

    Integer farAdd(FarEntity farEntity);

    Integer farEdit(FarEntity farEntity);

    Integer farDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<FarEntity> farAllList(FarEntity farEntity);
}
