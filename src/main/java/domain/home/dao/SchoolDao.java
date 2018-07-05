package domain.home.dao;

import domain.home.entity.SchoolEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolDao {
    /**
     * 学校风采分页个数
     * @param schoolEntity 分页实体
     * @return Integer
     */
    Integer schoolCount(SchoolEntity schoolEntity);

    /**
     * 学校风采分页
     * @param schoolEntity 分页实体
     * @return List<SchoolEntity>
     */
    List<SchoolEntity> schoolList(SchoolEntity schoolEntity);

    /**
     * 学校风采新增
     * @param schoolEntity 新增实体
     * @return Integer
     */
    Integer schoolAdd(SchoolEntity schoolEntity);
}
