package domain.home.service;

import domain.home.entity.RulesEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface RulesManagementService {
    /**
     * 规章制度分页
     * @param rulesEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult rulesList(RulesEntity rulesEntity);

    /**
     * 规章制度新增
     * @param rulesEntity 新增实体
     * @return Boolean
     */
    Boolean rulesAdd(RulesEntity rulesEntity);

    /**
     * 规章制度修改
     * @param rulesEntity 修改实体
     * @return Boolean
     */
    Boolean rulesEdit(RulesEntity rulesEntity);

    /**
     * 规章制度删除
     * @param id id
     * @param loginId 当前等id
     * @return Boolean
     */
    Boolean rulesDelete(Long id, Long loginId);

    /**
     * 规章制度接口
     * @param rulesEntity 查询实体
     * @return List<RulesEntity>
     */
    List<RulesEntity> rulesAllList(RulesEntity rulesEntity);

    RulesEntity rulesDetails(Long id);
}
