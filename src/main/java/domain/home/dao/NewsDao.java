package domain.home.dao;

import domain.home.entity.NewsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao {
    /**
     * 新闻管理分页个数
     * @param newsEntity newsEntity
     * @return Integer
     */
    Integer newsCount(NewsEntity newsEntity);

    /**
     * 新闻管理分页
     * @param newsEntity 分页实体
     * @return List<NewsEntity>
     */
    List<NewsEntity> newsListInfo(NewsEntity newsEntity);

    /**
     * 新闻新增
     * @param newsEntity 新增实体
     * @return Integer
     */
    Integer newsAdd(NewsEntity newsEntity);

    /**
     * 新闻修改
     * @param newsEntity 修改实体
     * @return Integer
     */
    Integer newsEdit(NewsEntity newsEntity);
}
