package domain.home.dao;

import domain.home.entity.CommunityEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityDao {
    Integer communityCount(CommunityEntity communityEntity);

    List<CommunityEntity> communityListInfo(CommunityEntity communityEntity);

    Integer communityAdd(CommunityEntity communityEntity);

    Integer communityEdit(CommunityEntity communityEntity);

    Integer communityDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<CommunityEntity> communityAllList(CommunityEntity communityEntity);

    CommunityEntity communityDetails(Long id);
}
