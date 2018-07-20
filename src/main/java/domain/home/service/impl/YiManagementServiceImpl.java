package domain.home.service.impl;

import domain.home.dao.YiDao;
import domain.home.entity.YiEntity;
import domain.home.service.YiManagementService;
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
public class YiManagementServiceImpl implements YiManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(YiManagementServiceImpl.class);

    final private YiDao yiDao;

    @Autowired
    public YiManagementServiceImpl(YiDao yiDao){
        this.yiDao = yiDao;
    }

    @Override
    public PageQueryResult yiList(YiEntity yiEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = yiDao.yiCount(yiEntity);

        List<YiEntity> yiEntities = newArrayList();

        if (count > 0){
            yiEntities = yiDao.yiList(yiEntity);
        }

        pageQueryResult.setRows(yiEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean yiAdd(YiEntity yiEntity) {
        final Boolean flag = yiDao.yiAdd(yiEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用邑新增:",flag);
        }
        return flag;
    }

    @Override
    public Boolean yiEdit(YiEntity yiEntity) {
        final Boolean flag = yiDao.yiEdit(yiEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用邑修改:",flag);
        }
        return flag;
    }

    @Override
    public Boolean yiDelete(Long id, Long loginId) {
        final Boolean flag = yiDao.yiDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("致用邑删除:",flag);
        }
        return flag;
    }

    @Override
    public List<YiEntity> yiAllList(YiEntity yiEntity) {
        return yiDao.yiAllList(yiEntity);
    }

    @Override
    public YiEntity yiDetails(Long id) {
        return yiDao.yiDetails(id);
    }
}
