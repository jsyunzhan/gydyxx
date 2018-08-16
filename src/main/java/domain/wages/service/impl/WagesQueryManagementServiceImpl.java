package domain.wages.service.impl;

import domain.wages.dao.WagesDao;
import domain.wages.entity.WagesEntity;
import domain.wages.entity.WagesQueryEntity;
import domain.wages.service.WagesQueryManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WagesQueryManagementServiceImpl implements WagesQueryManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WagesQueryManagementServiceImpl.class);

    final private WagesDao wagesDao;

    @Autowired
    public WagesQueryManagementServiceImpl(WagesDao wagesDao){
        this.wagesDao = wagesDao;
    }

    @Override
    public List<WagesEntity> wagesList(WagesQueryEntity wagesQueryEntity) {
        return wagesDao.wagesListByAccountId(wagesQueryEntity);
    }
}
