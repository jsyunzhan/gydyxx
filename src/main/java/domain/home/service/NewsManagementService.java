package domain.home.service;

import domain.home.entity.NewsEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface NewsManagementService {
    /**
     * 新闻中心分页
     * @param newsEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult newsListInfo(NewsEntity newsEntity);

    /**
     * 新闻中心新增
     * @param newsEntity 新增实体
     * @return Boolean
     */
    Boolean newsAdd(NewsEntity newsEntity);

    /**
     * 新闻中心修改
     * @param newsEntity 修改实体
     * @return Boolean
     */
    Boolean newsEdit(NewsEntity newsEntity);

    /**
     * 新闻中心删除
     * @param id id
     * @param loginId 登录id
     * @return Boolean
     */
    Boolean newsDelete(Long id, Long loginId);

    /**
     * 新闻中心接口
     * @param newsEntity 查询实体
     * @return List<NewsEntity>
     */
    List<NewsEntity> newsAllList(NewsEntity newsEntity);

    NewsEntity newsDetails(Long id);

    /**
     * 设置主标题
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean setMain(Long id, Long loginId);

    /**
     * 取消主标题
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean cancelMain(Long id, Long loginId);

    /**
     * 设置轮播图
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean setChange(Long id, Long loginId);

    /**
     * 取消轮播图
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean changeChange(Long id, Long loginId);

    /**
     * 主标题
     * @param newsEntity
     * @return
     */
    List<NewsEntity> newsMainList(NewsEntity newsEntity);

    /**
     * 轮播图
     * @param newsEntity
     * @return
     */
    List<NewsEntity> newsChangeList(NewsEntity newsEntity);
}
