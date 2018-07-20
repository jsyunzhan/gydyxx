package domain.home.service.impl;

import domain.home.dao.TrainingDao;
import domain.home.entity.TrainingEntity;
import domain.home.service.TrainingManagementService;
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
public class TrainingManagementServiceImpl implements TrainingManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingManagementServiceImpl.class);

    final private TrainingDao trainingDao;

    @Autowired
    public TrainingManagementServiceImpl(TrainingDao trainingDao){
        this.trainingDao = trainingDao;
    }

    @Override
    public PageQueryResult trainingList(TrainingEntity trainingEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<TrainingEntity> trainingEntities = newArrayList();

        final Integer count = trainingDao.trainingCount(trainingEntity);

        if (count > 0){
            trainingEntities = trainingDao.trainingList(trainingEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(trainingEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean trainingAdd(TrainingEntity trainingEntity) {
        final Boolean flag = trainingDao.trainingAdd(trainingEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校本培训新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean trainingEdit(TrainingEntity trainingEntity) {
        final Boolean flag = trainingDao.trainingEdit(trainingEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校本培训修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean trainingDelete(Long id, Long loginId) {
        final Boolean flag = trainingDao.trainingDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("校本培训删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<TrainingEntity> trainingAllList(TrainingEntity trainingEntity) {
        return trainingDao.trainingAllList(trainingEntity);
    }

    @Override
    public TrainingEntity trainingDetails(Long id) {
        return trainingDao.trainingDetails(id);
    }
}
