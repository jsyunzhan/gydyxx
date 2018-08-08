package domain.home.dao;

import domain.home.entity.SearchEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {
    Integer searchAdd(@Param("id") Long id,@Param("title")String title,@Param("url") String url);

    Integer searchEdit(@Param("id") Long id,@Param("title")String title,@Param("url") String url);

    List<SearchEntity> searchList(String title);

    Integer searchDelete(@Param("id")Long id,@Param("url") String url);
}
