package domain.home.dao;

import domain.home.entity.PartyEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartyDao {
    Integer partyCount(PartyEntity partyEntity);

    List<PartyEntity> partyList(PartyEntity partyEntity);

    Integer partyAdd(PartyEntity partyEntity);

    Integer partyEdit(PartyEntity partyEntity);

    Integer partyDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<PartyEntity> partyAllList(PartyEntity partyEntity);
}
