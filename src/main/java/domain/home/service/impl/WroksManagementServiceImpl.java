package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.dao.WroksDao;
import domain.home.entity.WorksEntity;
import domain.home.service.WroksManagementService;
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
public class WroksManagementServiceImpl implements WroksManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WroksManagementServiceImpl.class);

    final private WroksDao wroksDao;
    final private SearchDao searchDao;

    @Autowired
    public WroksManagementServiceImpl(WroksDao wroksDao,SearchDao searchDao){
        this.wroksDao = wroksDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult worksListInfo(WorksEntity worksEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<WorksEntity> worksEntities = newArrayList();

        final Integer count = wroksDao.worksCount(worksEntity);

        if (count > 0){
            worksEntities = wroksDao.worksListInfo(worksEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(worksEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean worksAdd(WorksEntity worksEntity) {

        final Boolean flag = wroksDao.worksAdd(worksEntity) > 0;

        if (flag){
            searchDao.searchAdd(worksEntity.getId(),worksEntity.getWorksTitle(),"/homepage/works/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("作品新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean worksEdit(WorksEntity worksEntity) {
        final Boolean flag = wroksDao.worksEdit(worksEntity) > 0;
        if (flag){
            searchDao.searchEdit(worksEntity.getId(),worksEntity.getWorksTitle(),"/homepage/works/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("作品修改结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean worksDelete(Long id, Long loginId) {
        final Boolean flag = wroksDao.worksDelete(id,loginId) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("作品删除结果:",flag);
        }

        return flag;
    }

    @Override
    public List<WorksEntity> worksAllList(WorksEntity worksEntity) {
        return wroksDao.worksAllList(worksEntity);
    }

    @Override
    public WorksEntity worksDetails(Long id) {
        return wroksDao.worksDetails(id);
    }
}
