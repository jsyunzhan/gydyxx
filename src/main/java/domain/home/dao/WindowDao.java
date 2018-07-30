package domain.home.dao;

import domain.home.entity.WindowEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WindowDao {
    Integer windowCount(WindowEntity windowEntity);

    List<WindowEntity> windowListInfo(WindowEntity windowEntity);

    Integer windowAdd(WindowEntity windowEntity);

    Integer windowEdit(WindowEntity windowEntity);

    Integer windowDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<WindowEntity> windowAllList(WindowEntity windowEntity);
}
