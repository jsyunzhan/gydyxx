package domain.home.dao;

import domain.home.entity.HistoryEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryDao {
    Integer historyCount(HistoryEntity historyEntity);

    List<HistoryEntity> historyList(HistoryEntity historyEntity);

    Integer historyAdd(HistoryEntity historyEntity);

    Integer historyEdit(HistoryEntity historyEntity);

    Integer historyDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<HistoryEntity> historyAllList(HistoryEntity historyEntity);

    HistoryEntity historyDetails(Long id);
}
