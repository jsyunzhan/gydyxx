package domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class readme {

    @RequestMapping(value = "/security/movetologin")
    public ModelAndView index(){
        return new ModelAndView("home/homepage");
    }
}
