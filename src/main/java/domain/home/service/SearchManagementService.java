package domain.home.service;

import domain.home.entity.SearchEntity;

import java.util.List;

public interface SearchManagementService {
    List<SearchEntity> searchList(String title);
}
