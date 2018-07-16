package domain.home.dao;

import domain.home.entity.ProfileEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileDao {
    Integer profileCount(ProfileEntity profileEntity);

    List<ProfileEntity> profileList(ProfileEntity profileEntity);

    Integer profileAdd(ProfileEntity profileEntity);

    Integer profileEdit(ProfileEntity profileEntity);

    Integer profileDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<ProfileEntity> profileAllList(ProfileEntity profileEntity);
}
