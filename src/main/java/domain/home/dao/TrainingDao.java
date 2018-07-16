package domain.home.dao;

import domain.home.entity.TrainingEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingDao {
    Integer trainingCount(TrainingEntity trainingEntity);

    List<TrainingEntity> trainingList(TrainingEntity trainingEntity);

    Integer trainingAdd(TrainingEntity trainingEntity);

    Integer trainingEdit(TrainingEntity trainingEntity);

    Integer trainingDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<TrainingEntity> trainingAllList(TrainingEntity trainingEntity);
}
