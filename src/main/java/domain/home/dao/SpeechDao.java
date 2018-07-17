package domain.home.dao;

import domain.home.entity.SpeechEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeechDao {

    Integer speechCount(SpeechEntity speechEntity);

    List<SpeechEntity> speechList(SpeechEntity speechEntity);

    Integer speechAdd(SpeechEntity speechEntity);

    Integer speechEdit(SpeechEntity speechEntity);

    Integer speechDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<SpeechEntity> speechAllList(SpeechEntity speechEntity);
}
