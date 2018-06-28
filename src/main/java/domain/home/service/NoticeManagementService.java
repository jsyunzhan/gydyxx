package domain.home.service;

import domain.home.entity.NoticeEntity;
import domain.shiro.entity.PageQueryResult;

public interface NoticeManagementService {
    /**
     * 首页公告分页
     * @param noticeEntity noticeEntity
     * @return PageQueryResult
     */
    PageQueryResult noticeListInfo(NoticeEntity noticeEntity);
}
