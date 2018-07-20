package domain.home.dao;

import domain.home.entity.CelebrateEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrateDao {
    Integer celebrateCount(CelebrateEntity celebrateEntity);

    List<CelebrateEntity> celebrateInfo(CelebrateEntity celebrateEntity);

    Integer celebrateAdd(CelebrateEntity celebrateEntity);

    Integer celebrateEdit(CelebrateEntity celebrateEntity);

    Integer celebrateDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<CelebrateEntity> celebrateAllList(CelebrateEntity celebrateEntity);

    CelebrateEntity celebrateDetails(Long id);
}
