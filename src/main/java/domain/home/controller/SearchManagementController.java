package domain.home.controller;

import domain.home.entity.SearchEntity;
import domain.home.service.SearchManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchManagementController {

    final private SearchManagementService searchManagementService;

    @Autowired
    public SearchManagementController(SearchManagementService searchManagementService){
        this.searchManagementService = searchManagementService;
    }



    @RequestMapping(value = "/homepage/home/search")
    @ResponseBody
    public List<SearchEntity> searchList(@RequestParam("title") String title){
        return searchManagementService.searchList(title);
    }
}
