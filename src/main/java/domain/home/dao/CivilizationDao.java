package domain.home.dao;

import domain.home.entity.CivilizationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CivilizationDao {
    Integer civilizationCount(CivilizationEntity civilizationEntity);

    List<CivilizationEntity> civilizationList(CivilizationEntity civilizationEntity);

    Integer civilizationAdd(CivilizationEntity civilizationEntity);
}
