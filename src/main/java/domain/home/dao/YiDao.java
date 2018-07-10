package domain.home.dao;

import domain.home.entity.YiEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YiDao {

    Integer yiCount(YiEntity yiEntity);

    List<YiEntity> yiList(YiEntity yiEntity);

    Integer yiAdd(YiEntity yiEntity);

    Integer yiEdit(YiEntity yiEntity);

    Integer yiDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
