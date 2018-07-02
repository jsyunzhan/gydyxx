package domain.home.service.impl;

import domain.home.dao.WroksDao;
import domain.home.service.WroksManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WroksManagementServiceImpl implements WroksManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(WroksManagementServiceImpl.class);

    final private WroksDao wroksDao;

    @Autowired
    public WroksManagementServiceImpl(WroksDao wroksDao){
        this.wroksDao = wroksDao;
    }

}
