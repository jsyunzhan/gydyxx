package domain.home.service.impl;

import domain.home.dao.NewsDao;
import domain.home.entity.NewsEntity;
import domain.home.service.NewsManagementService;
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
public class NewsManagementServiceImpl implements NewsManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsManagementServiceImpl.class);

    final private NewsDao newsDao;

    @Autowired
    public NewsManagementServiceImpl(NewsDao newsDao){
        this.newsDao = newsDao;
    }

    @Override
    public PageQueryResult newsListInfo(NewsEntity newsEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = newsDao.newsCount(newsEntity);

        List<NewsEntity> newsEntities = newArrayList();

        if (count > 0){
            newsEntities = newsDao.newsListInfo(newsEntity);
        }

        pageQueryResult.setRows(newsEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean newsAdd(NewsEntity newsEntity) {

        final Boolean flag = newsDao.newsAdd(newsEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("新闻新增:",flag);
        }

        return flag;
    }

    @Override
    public Boolean newsEdit(NewsEntity newsEntity) {
        final Boolean flag = newsDao.newsEdit(newsEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("新闻修改:",flag);
        }

        return flag;
    }

    @Override
    public Boolean newsDelete(Long id, Long loginId) {

        final Boolean flag = newsDao.newsDelete(id,loginId) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("新闻删除:",flag);
        }

        return flag;
    }

    @Override
    public List<NewsEntity> newsAllList(NewsEntity newsEntity) {
        return newsDao.newsAllList(newsEntity);
    }
}
