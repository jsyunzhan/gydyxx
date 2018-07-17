package domain.home.service;

import domain.home.entity.EmailEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface EmailManagementService {
    /**
     * 校长信箱
     * @param emailEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult emailList(EmailEntity emailEntity);

    List<EmailEntity> emailAllList(EmailEntity emailEntity);
}
