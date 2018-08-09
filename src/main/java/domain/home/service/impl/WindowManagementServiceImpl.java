package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.dao.WindowDao;
import domain.home.entity.WindowEntity;
import domain.home.service.WindowManagementService;
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
public class WindowManagementServiceImpl implements WindowManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WindowManagementServiceImpl.class);

    final private WindowDao windowDao;
    final private SearchDao searchDao;


    @Autowired
    public WindowManagementServiceImpl(WindowDao windowDao,SearchDao searchDao){
        this.windowDao = windowDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult windowList(WindowEntity windowEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = windowDao.windowCount(windowEntity);

        List<WindowEntity> windowEntities = newArrayList();

        if (count > 0){
            windowEntities = windowDao.windowListInfo(windowEntity);
        }

        pageQueryResult.setRows(windowEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean windowAdd(WindowEntity windowEntity) {
        final Boolean flag = windowDao.windowAdd(windowEntity) > 0;
        if (flag){
            searchDao.searchAdd(windowEntity.getId(),windowEntity.getWindowTitle(),"/homepage/window/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("飘窗新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean windowEdit(WindowEntity windowEntity) {
        final Boolean flag = windowDao.windowEdit(windowEntity) > 0;
        if (flag){
            searchDao.searchEdit(windowEntity.getId(),windowEntity.getWindowTitle(),"/homepage/window/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("飘窗修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean windowDelete(Long id, Long loginId) {
        final Boolean flag = windowDao.windowDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/window/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("飘窗删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<WindowEntity> windowAllList(WindowEntity windowEntity) {
        return windowDao.windowAllList(windowEntity);
    }

    @Override
    public Boolean windowOpen(Long id, Long loginId) {
        final Boolean flag = windowDao.windowOpen(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("飘窗结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean windowClose(Long id, Long loginId) {
        final Boolean flag = windowDao.windowClose(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("飘窗结果:",flag);
        }
        return flag;
    }

    @Override
    public WindowEntity windowDetails(Long id) {
        return windowDao.windowDetails(id);
    }
}
