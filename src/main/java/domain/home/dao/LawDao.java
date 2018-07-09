package domain.home.dao;

import domain.home.entity.LawEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawDao {
    Integer lawCount(LawEntity lawEntity);

    List<LawEntity> lawList(LawEntity lawEntity);

    Integer lawAdd(LawEntity lawEntity);

    Integer lawEdit(LawEntity lawEntity);

    Integer lawDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
