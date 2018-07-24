package domain.home.service.impl;

import domain.home.dao.SearchDao;
import domain.home.entity.SearchEntity;
import domain.home.service.SearchManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

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

        List<SearchEntity> searchEntities = newArrayList();

        //符合条件的
        final List<SearchEntity> list = searchDao.searchList(title);

        for (SearchEntity searchEntity:
        list) {
            final SearchEntity searchEntity1 = searchDao.searchDetails(searchEntity.getTableId(),searchEntity.getTableName());
            searchEntities.add(searchEntity1);
        }


        return searchEntities;
    }
}
