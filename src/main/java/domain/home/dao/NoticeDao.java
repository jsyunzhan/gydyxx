package domain.home.dao;

import domain.home.entity.NoticeEntity;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 首页公告新增
     * @param noticeEntity noticeEntity
     * @return Integer
     */
    Integer noticeAdd(NoticeEntity noticeEntity);

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
     * @return Integer
     */
    Integer noticeDelete(@Param("id") Long id,@Param("updateUserId") Long loginId);
}
