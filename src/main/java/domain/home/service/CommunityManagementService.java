package domain.home.service;

import domain.home.entity.CommunityEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface CommunityManagementService {
    /**
     * 精品社团分页
     * @param communityEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult communityList(CommunityEntity communityEntity);

    /**
     * 精品社团新增
     * @param communityEntity 新增实体
     * @return Boolean
     */
    Boolean communityAdd(CommunityEntity communityEntity);

    /**
     * 精品社团修改
     * @param communityEntity 修改实体
     * @return Boolean
     */
    Boolean communityEdit(CommunityEntity communityEntity);

    /**
     * 精品校园删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean communityDelete(Long id, Long loginId);

    List<CommunityEntity> communityAllList(CommunityEntity communityEntity);

    CommunityEntity communityDetails(Long id);
}
