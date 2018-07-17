package domain.home.dao;

import domain.home.entity.HomeschoolEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeschoolDao {
    Integer homeschoolCount(HomeschoolEntity homeschoolEntity);

    List<HomeschoolEntity> homeschoolList(HomeschoolEntity homeschoolEntity);

    Integer homeschoolAdd(HomeschoolEntity homeschoolEntity);

    Integer homeschoolEdit(HomeschoolEntity homeschoolEntity);

    Integer homeschoolDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<HomeschoolEntity> homeschoolAllList(HomeschoolEntity homeschoolEntity);
}
