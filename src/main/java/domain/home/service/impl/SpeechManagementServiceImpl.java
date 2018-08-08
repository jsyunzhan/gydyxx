package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.dao.SpeechDao;
import domain.home.entity.SpeechEntity;
import domain.home.service.SpeechManagementService;
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
public class SpeechManagementServiceImpl implements SpeechManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SpeechManagementServiceImpl.class);

    final private SpeechDao speechDao;
    final private SearchDao searchDao;

    @Autowired
    public SpeechManagementServiceImpl(SpeechDao speechDao,SearchDao searchDao){
        this.speechDao = speechDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult speechList(SpeechEntity speechEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<SpeechEntity> speechEntities = newArrayList();

        final Integer count = speechDao.speechCount(speechEntity);

        if (count > 0){
            speechEntities = speechDao.speechList(speechEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(speechEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean speechAdd(SpeechEntity speechEntity) {
        final Boolean flag = speechDao.speechAdd(speechEntity) > 0;

        if (flag){
            searchDao.searchAdd(speechEntity.getId(),speechEntity.getSpeechTitle(),"/homepage/speech/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("国旗下讲话新增:",flag);
        }
        return flag;
    }

    @Override
    public Boolean speechEdit(SpeechEntity speechEntity) {
        final Boolean flag = speechDao.speechEdit(speechEntity) > 0;
        if (flag){
            searchDao.searchEdit(speechEntity.getId(),speechEntity.getSpeechTitle(),"/homepage/speech/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("国旗下讲话修改:",flag);
        }
        return flag;
    }

    @Override
    public Boolean speechDelete(Long id, Long loginId) {
        final Boolean flag = speechDao.speechDelete(id,loginId) > 0;
        if (flag){
            searchDao.searchDelete(id,"/homepage/speech/details/");
        }
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("国旗下讲话删除:",flag);
        }
        return flag;
    }

    @Override
    public List<SpeechEntity> speechAllList(SpeechEntity speechEntity) {
        return speechDao.speechAllList(speechEntity);
    }

    @Override
    public SpeechEntity speechDetails(Long id) {
        return speechDao.speechDetails(id);
    }
}
