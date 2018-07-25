package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.entity.SearchEntity;
import domain.home.service.SearchManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SearchManagementServiceImpl implements SearchManagementService{


    final private SearchDao searchDao;

    @Autowired
    public SearchManagementServiceImpl(SearchDao searchDao){
        this.searchDao = searchDao;
    }

    @Override
    public List<SearchEntity> searchList(String title) {
        return searchDao.searchList(title);
    }
}
