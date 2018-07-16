package domain.home.service;

import domain.home.entity.PartyEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface PartyManagementService {
    /**
     * 党建工会分页
     * @param partyEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult partyList(PartyEntity partyEntity);

    /**
     * 党建工会新增
     * @param partyEntity 新增实体
     * @return Boolean
     */
    Boolean partyAdd(PartyEntity partyEntity);

    /**
     * 党建工会修改
     * @param partyEntity 修改实体
     * @return Boolean
     */
    Boolean partyEdit(PartyEntity partyEntity);

    /**
     * 党建公户删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean partyDelete(Long id, Long loginId);

    List<PartyEntity> partyAllList(PartyEntity partyEntity);
}
