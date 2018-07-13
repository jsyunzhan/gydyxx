package domain.home.service.impl;

import domain.home.dao.EmailDao;
import domain.home.entity.EmailEntity;
import domain.home.service.EmailManagementService;
import domain.shiro.entity.PageQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
@Transactional
public class EmailManagementServiceImpl implements EmailManagementService{

    final private EmailDao emailDao;

    @Autowired
    public EmailManagementServiceImpl(EmailDao emailDao){
        this.emailDao = emailDao;
    }

    @Override
    public PageQueryResult emailList(EmailEntity emailEntity) {
        PageQueryResult pageQueryResult = new PageQueryResult();

        List<EmailEntity> emailEntities = newArrayList();

        final Integer count = emailDao.emailCount(emailEntity);

        if (count > 0){
            emailEntities = emailDao.emailList(emailEntity);
        }

        pageQueryResult.setTotal(count);
        pageQueryResult.setRows(emailEntities);
        return pageQueryResult;
    }
}
