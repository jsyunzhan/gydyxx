package domain.home.service;

import domain.home.entity.CelebrateEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface CelebrateManagementService {
    /**
     * 校园节庆分页
     * @param celebrateEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult celebrateList(CelebrateEntity celebrateEntity);

    /**
     * 校园节庆新增
     * @param celebrateEntity 新增实体
     * @return Boolean
     */
    Boolean celebrateAdd(CelebrateEntity celebrateEntity);

    /**
     * 校园节庆修改
     * @param celebrateEntity 修改实体
     * @return Boolean
     */
    Boolean celebrateEdit(CelebrateEntity celebrateEntity);

    /**
     * 校园节庆删除
     * @param id id
     * @param loginId
     * @return
     */
    Boolean celebrateDelete(Long id, Long loginId);

    List<CelebrateEntity> celebrateAllList(CelebrateEntity celebrateEntity);

    CelebrateEntity celebrateDetails(Long id);
}
