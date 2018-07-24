package domain.home.dao;

import domain.home.entity.SearchEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {
    Integer searchAdd(@Param("id") Long id,@Param("title")String title,@Param("tableName") String t_home_notice);

    List<SearchEntity> searchList(String title);


    SearchEntity searchDetails(@Param("id") String tableId,@Param("tableName") String tableName);
}
