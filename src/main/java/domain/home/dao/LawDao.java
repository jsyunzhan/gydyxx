package domain.home.dao;

import domain.home.entity.LawEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawDao {
    Integer lawCount(LawEntity lawEntity);

    List<LawEntity> lawList(LawEntity lawEntity);
}
