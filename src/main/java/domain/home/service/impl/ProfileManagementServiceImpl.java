package domain.home.service.impl;

import domain.home.dao.ProfileDao;
import domain.home.dao.SearchDao;
import domain.home.entity.ProfileEntity;
import domain.home.service.ProfileManagementService;
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
public class ProfileManagementServiceImpl implements ProfileManagementService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileManagementServiceImpl.class);

    final private ProfileDao profileDao;
    final private SearchDao searchDao;

    @Autowired
    public ProfileManagementServiceImpl(ProfileDao profileDao,SearchDao searchDao){
        this.profileDao =profileDao;
        this.searchDao = searchDao;
    }

    @Override
    public PageQueryResult profileList(ProfileEntity profileEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<ProfileEntity> profileEntities = newArrayList();

        final Integer count = profileDao.profileCount(profileEntity);

        if (count > 0){
            profileEntities = profileDao.profileList(profileEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(profileEntities);
        return pageQueryResult;
    }

    @Override
    public Boolean profileAdd(ProfileEntity profileEntity) {
        final Boolean flag = profileDao.profileAdd(profileEntity) > 0;

        if (flag){
            searchDao.searchAdd(profileEntity.getId(),profileEntity.getProfileTitle(),"/homepage/profile/details/");
        }

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学校概况新增结果:",flag);
        }

        return flag;
    }

    @Override
    public Boolean profileEdit(ProfileEntity profileEntity) {
        final Boolean flag = profileDao.profileEdit(profileEntity) > 0;

        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学校概况修改结果:",flag);
        }
        return flag;
    }

    @Override
    public Boolean profileDelete(Long id, Long loginId) {
        final Boolean flag = profileDao.profileDelete(id,loginId) > 0;
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("学校概删除结果:",flag);
        }
        return flag;
    }

    @Override
    public List<ProfileEntity> profileAllList(ProfileEntity profileEntity) {
        return profileDao.profileAllList(profileEntity);
    }

    @Override
    public ProfileEntity profileDetails(Long id) {
        return profileDao.profileDetails(id);
    }
}
