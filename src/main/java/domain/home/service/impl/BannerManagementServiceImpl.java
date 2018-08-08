package domain.home.service.impl;

import domain.home.dao.BannerDao;
import domain.home.dao.SearchDao;
import domain.home.entity.BannerEntity;
import domain.home.service.BannerManagementService;
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
public class BannerManagementServiceImpl implements BannerManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(BannerManagementServiceImpl.class);

    final private BannerDao bannerDao;
    final private SearchDao searchDao;

    @Autowired
    public BannerManagementServiceImpl(BannerDao bannerDao,SearchDao searchDao){
        this.bannerDao = bannerDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult bannerList(BannerEntity bannerEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<BannerEntity> bannerEntities = newArrayList();

        final Integer count = bannerDao.bannerCount(bannerEntity);

        if (count > 0){
            bannerEntities = bannerDao.bannerList(bannerEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(bannerEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean bannerAdd(BannerEntity bannerEntity) {
        final Boolean flag = bannerDao.bannerAdd(bannerEntity) > 0;
        if (flag){
            searchDao.searchAdd(bannerEntity.getId(),bannerEntity.getBannerTitle(),"/homepage/banner/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("轮播图新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean bannerEdit(BannerEntity bannerEntity) {
        final Boolean flag = bannerDao.bannerEdit(bannerEntity) > 0;
        if (flag){
            searchDao.searchEdit(bannerEntity.getId(),bannerEntity.getBannerTitle(),"/homepage/banner/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("轮播图修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean bannerDelete(Long id, Long loginId) {
        final Boolean flag = bannerDao.bannerDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/banner/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("轮播图删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<BannerEntity> bannerAllList(BannerEntity bannerEntity) {
        return bannerDao.bannerAllList(bannerEntity);
    }

    @Override
    public Boolean bannerOpen(Long id, Long loginId) {
        final Boolean flag = bannerDao.bannerOpen(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("轮播图开启结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean bannerClose(Long id, Long loginId) {
        final Boolean flag = bannerDao.bannerClose(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("轮播图暂停结果:",flag);
        }
        return flag;
    }
}
