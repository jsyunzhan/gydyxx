package domain.home.dao;

import domain.home.entity.CivilizationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CivilizationDao {
    Integer civilizationCount(CivilizationEntity civilizationEntity);

    List<CivilizationEntity> civilizationList(CivilizationEntity civilizationEntity);

    Integer civilizationAdd(CivilizationEntity civilizationEntity);

    Integer civilizationEdit(CivilizationEntity civilizationEntity);

    Integer civilizationDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<CivilizationEntity> civilizationAllList(CivilizationEntity civilizationEntity);

    CivilizationEntity civilizationDetails(Long id);
}
