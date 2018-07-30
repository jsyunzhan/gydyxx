package domain.home.service;

import domain.home.entity.BannerEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface BannerManagementService {
    /**
     * 轮播图分页
     * @param bannerEntity 查询实体
     * @return PageQueryResult
     */
    PageQueryResult bannerList(BannerEntity bannerEntity);

    /**
     * 轮播图新增
     * @param bannerEntity 新增实体
     * @return Boolean
     */
    Boolean bannerAdd(BannerEntity bannerEntity);

    /**
     * 轮播图修改
     * @param bannerEntity 修改实体
     * @return Boolean
     */
    Boolean bannerEdit(BannerEntity bannerEntity);

    /**
     * 轮播图删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean bannerDelete(Long id, Long loginId);

    List<BannerEntity> bannerAllList(BannerEntity bannerEntity);
}
