package domain.home.service;

import domain.home.entity.EmailEntity;
import domain.shiro.entity.PageQueryResult;

public interface EmailManagementService {
    /**
     * 校长信箱
     * @param emailEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult emailList(EmailEntity emailEntity);
}
