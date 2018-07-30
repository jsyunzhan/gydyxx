package domain.home.dao;

import domain.home.entity.BannerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerDao {

    Integer bannerCount(BannerEntity bannerEntity);

    List<BannerEntity> bannerList(BannerEntity bannerEntity);

    Integer bannerAdd(BannerEntity bannerEntity);

    Integer bannerEdit(BannerEntity bannerEntity);

    Integer bannerDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<BannerEntity> bannerAllList(BannerEntity bannerEntity);

    Integer bannerOpen(@Param("id") Long id,@Param("updateUserId") Long loginId);

    Integer bannerClose(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
