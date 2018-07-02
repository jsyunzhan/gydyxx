package domain.home.dao;

import domain.home.entity.WorksEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WroksDao {
    /**
     * 作品分页个数
     * @param worksEntity 分页实体
     * @return Integer
     */
    Integer worksCount(WorksEntity worksEntity);

    /**
     * 作品分页
     * @param worksEntity 分页实体
     * @return List<WorksEntity>
     */
    List<WorksEntity> worksListInfo(WorksEntity worksEntity);

    /**
     * 作品新增
     * @param worksEntity 新增实体
     * @return Integer
     */
    Integer worksAdd(WorksEntity worksEntity);
}
