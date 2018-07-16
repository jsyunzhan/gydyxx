package domain.home.dao;

import domain.home.entity.WorksEntity;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 作品修改
     * @param worksEntity 修改实体
     * @return Integer
     */
    Integer worksEdit(WorksEntity worksEntity);

    /**
     * 作品删除
     * @param id id
     * @param loginId 当前登录id
     * @return Integer
     */
    Integer worksDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);

    List<WorksEntity> worksAllList(WorksEntity worksEntity);
}
