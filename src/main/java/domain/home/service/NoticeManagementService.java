package domain.home.service;

import domain.home.entity.NoticeEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface NoticeManagementService {
    /**
     * 首页公告分页
     * @param noticeEntity noticeEntity
     * @return PageQueryResult
     */
    PageQueryResult noticeListInfo(NoticeEntity noticeEntity);

    /**
     * 首页公告新增
     * @param noticeEntity 新增实体
     * @return Boolean
     */
    Boolean noticeAdd(NoticeEntity noticeEntity);

    /**
     * 首页公告修改
     * @param noticeEntity 修改实体
     * @return Boolean
     */
    Boolean noticeEdit(NoticeEntity noticeEntity);

    /**
     * 首页公告删除
     * @param id id
     * @param loginId 更新id
     * @return Boolean
     */
    Boolean noticeDelete(Long id, Long loginId);

    /**
     * 通知公告主页
     * @param noticeEntity 查询实体
     * @return List<NoticeEntity>
     */
    List<NoticeEntity> noticeAllList(NoticeEntity noticeEntity);

    NoticeEntity noticeDetails(Long id);
}
