package domain.home.dao;

import domain.home.entity.ResourcesEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourcesDao {
    Integer resourcesCount(ResourcesEntity resourcesEntity);

    List<ResourcesEntity> resourcesList(ResourcesEntity resourcesEntity);

    Integer resourcesAdd(ResourcesEntity resourcesEntity);

    Integer resourcesEdit(ResourcesEntity resourcesEntity);

    Integer resourcesDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<ResourcesEntity> resourcesAllList(ResourcesEntity resourcesEntity);

    ResourcesEntity resourcesDetails(Long id);
}
