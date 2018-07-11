package domain.home.service.impl;

import domain.home.dao.CommunityDao;
import domain.home.entity.CommunityEntity;
import domain.home.service.CommunityManagementService;
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
public class CommunityManagementServiceImpl implements CommunityManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityManagementServiceImpl.class);

    final private CommunityDao communityDao;

    @Autowired
    public CommunityManagementServiceImpl(CommunityDao communityDao){
        this.communityDao = communityDao;
    }

    @Override
    public PageQueryResult communityList(CommunityEntity communityEntity) {
        final PageQueryResult pageQueryResult = new PageQueryResult();

        final Integer count = communityDao.communityCount(communityEntity);

        List<CommunityEntity> communityEntities = newArrayList();

        if (count > 0){
            communityEntities = communityDao.communityListInfo(communityEntity);
        }

        pageQueryResult.setRows(communityEntities);
        pageQueryResult.setTotal(count);
        return pageQueryResult;
    }

    @Override
    public Boolean communityAdd(CommunityEntity communityEntity) {
        final Boolean flag = communityDao.communityAdd(communityEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("精品社团新增结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean communityEdit(CommunityEntity communityEntity) {
        final Boolean flag = communityDao.communityEdit(communityEntity) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("精品社团修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean communityDelete(Long id, Long loginId) {
        final Boolean flag = communityDao.communityDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("精品社团删除结果:",flag);
        }
        return flag;
    }
}
