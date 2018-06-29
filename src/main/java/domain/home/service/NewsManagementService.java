package domain.home.service;

import domain.home.entity.NewsEntity;
import domain.shiro.entity.PageQueryResult;

public interface NewsManagementService {
    /**
     * 新闻中心分页
     * @param newsEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult newsListInfo(NewsEntity newsEntity);

    /**
     * 新增中心新增
     * @param newsEntity 新增实体
     * @return Boolean
     */
    Boolean newsAdd(NewsEntity newsEntity);
}
