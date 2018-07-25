package domain.home.service.impl;

import domain.home.dao.NoticeDao;
import domain.home.dao.SearchDao;
import domain.home.entity.NoticeEntity;
import domain.home.service.NoticeManagementService;
import domain.shiro.entity.PageQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Transactional
public class NoticeManagementServiceImpl implements NoticeManagementService{

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeManagementServiceImpl.class);

    final private NoticeDao noticeDao;
    final private SearchDao searchDao;

    @Autowired
    public NoticeManagementServiceImpl(NoticeDao noticeDao,SearchDao searchDao){
        this.noticeDao = noticeDao;
        this.searchDao = searchDao;
    }


    @Override
    public PageQueryResult noticeListInfo(NoticeEntity noticeEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<NoticeEntity> noticeEntities = newArrayList();

        final Integer count = noticeDao.noticeCount(noticeEntity);

        if (count > 0){
            noticeEntities = noticeDao.noticeListInfo(noticeEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(noticeEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean noticeAdd(NoticeEntity noticeEntity) {
        final Boolean flag = noticeDao.noticeAdd(noticeEntity) > 0;

        if (flag){
            searchDao.searchAdd(noticeEntity.getId(),noticeEntity.getNoticeTitle(),"/homepage/notice/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("公告新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean noticeEdit(NoticeEntity noticeEntity) {
        final Boolean flag = noticeDao.noticeEdit(noticeEntity);

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("公告修改结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean noticeDelete(Long id, Long loginId) {

        final Boolean flag = noticeDao.noticeDelete(id,loginId) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("公告删除结果:",flag);
        }

        return flag;
    }

    @Override
    public List<NoticeEntity> noticeAllList(NoticeEntity noticeEntity) {
        return noticeDao.noticeAllList(noticeEntity);
    }

    @Override
    public NoticeEntity noticeDetails(Long id) {
        return noticeDao.noticeDetails(id);
    }
}
