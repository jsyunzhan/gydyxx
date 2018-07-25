package domain.home.controller;

import com.alibaba.fastjson.JSONObject;
import domain.home.entity.SearchEntity;
import domain.home.service.SearchManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchManagementController {

    final private SearchManagementService searchManagementService;

    @Autowired
    public SearchManagementController(SearchManagementService searchManagementService){
        this.searchManagementService = searchManagementService;
    }



    @RequestMapping(value = "/homepage/home/search")
    @ResponseBody
    public ModelAndView searchList(@RequestParam("title") String title){
        final List<SearchEntity> searchEntities = searchManagementService.searchList(title);
        final Map<String, Object> map = new HashMap<>(1);
        map.put("result", JSONObject.toJSON(searchEntities));
        return new ModelAndView("pc/search/searchlist",map);
    }
}
