package domain.home.service;

import domain.home.entity.ProfileEntity;
import domain.shiro.entity.PageQueryResult;

public interface ProfileManagementService {
    /**
     * 学校概况分页
     * @param profileEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult profileList(ProfileEntity profileEntity);

    /**
     * 学校概况新增
     * @param profileEntity 新增实体
     * @return Boolean
     */
    Boolean profileAdd(ProfileEntity profileEntity);

    /**
     * 学校概况修改
     * @param profileEntity 修改实体
     * @return Boolean
     */
    Boolean profileEdit(ProfileEntity profileEntity);

    /**
     * 学校概况删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean profileDelete(Long id, Long loginId);
}