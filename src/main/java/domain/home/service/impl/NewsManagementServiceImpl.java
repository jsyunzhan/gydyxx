package domain.home.service.impl;

import domain.home.dao.NewsDao;
import domain.home.dao.SearchDao;
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
    final private SearchDao searchDao;

    @Autowired
    public NewsManagementServiceImpl(NewsDao newsDao,SearchDao searchDao){
        this.newsDao = newsDao;
        this.searchDao = searchDao;
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

        if (flag){
            searchDao.searchAdd(newsEntity.getId(),newsEntity.getNewsTitle(),"/homepage/news/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("新闻新增:",flag);
        }

        return flag;
    }

    @Override
    public Boolean newsEdit(NewsEntity newsEntity) {
        final Boolean flag = newsDao.newsEdit(newsEntity) > 0;
        if (flag){
            searchDao.searchEdit(newsEntity.getId(),newsEntity.getNewsTitle(),"/homepage/news/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("新闻修改:",flag);
        }

        return flag;
    }

    @Override
    public Boolean newsDelete(Long id, Long loginId) {

        final Boolean flag = newsDao.newsDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/news/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("新闻删除:",flag);
        }

        return flag;
    }

    @Override
    public List<NewsEntity> newsAllList(NewsEntity newsEntity) {
        return newsDao.newsAllList(newsEntity);
    }

    @Override
    public NewsEntity newsDetails(Long id) {
        return newsDao.newsDetails(id);
    }

    @Override
    public Boolean setMain(Long id, Long loginId) {
        Boolean flag = false;
        final Integer cout = newsDao.cancelALlMain(id,loginId);
        if (cout > 0){
            flag = newsDao.setMain(id,loginId) > 0;
        }
        return flag;
    }

    @Override
    public Boolean cancelMain(Long id, Long loginId) {
        return newsDao.cancelMain(id,loginId) > 0;
    }

    @Override
    public Boolean setChange(Long id, Long loginId) {
        return newsDao.setChange(id,loginId);
    }

    @Override
    public Boolean changeChange(Long id, Long loginId) {
        return newsDao.changeChange(id,loginId);
    }

    @Override
    public List<NewsEntity> newsMainList(NewsEntity newsEntity) {
        return newsDao.newsMainList(newsEntity);
    }

    @Override
    public List<NewsEntity> newsChangeList(NewsEntity newsEntity) {
        return newsDao.newsChangeList(newsEntity);
    }
}
