package domain.home.dao;

import domain.home.entity.NoticeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDao {
    /**
     * 首页公告个数
     * @param noticeEntity noticeEntity
     * @return Integer
     */
    Integer noticeCount(NoticeEntity noticeEntity);

    /**
     * 首页公告集合
     * @param noticeEntity noticeEntity
     * @return List<NoticeEntity>
     */
    List<NoticeEntity> noticeListInfo(NoticeEntity noticeEntity);
}
