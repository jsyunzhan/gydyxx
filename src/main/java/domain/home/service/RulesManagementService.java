package domain.home.service;

import domain.home.entity.RulesEntity;
import domain.shiro.entity.PageQueryResult;

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
}
