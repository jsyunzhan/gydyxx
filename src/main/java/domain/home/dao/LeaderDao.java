package domain.home.dao;

import domain.home.entity.LeaderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderDao {
    Integer newsCount(LeaderEntity leaderEntity);

    List<LeaderEntity> leaderList(LeaderEntity leaderEntity);

    Integer leaderAdd(LeaderEntity leaderEntity);

    Integer leaderEdit(LeaderEntity leaderEntity);

    Integer leaderDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
