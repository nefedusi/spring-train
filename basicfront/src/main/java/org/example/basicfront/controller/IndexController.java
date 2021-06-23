package org.example.basicfront.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping(path = "/index")
    public String indexPage() {
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("indexPageObject", "indexPageObjectValue");
//        modelAndView.setStatus(HttpStatus.OK);
//        return modelAndView;
        return "index";
    }

    @GetMapping(path = "/ping")
    @ResponseBody
    public String ping() {
        return "Ping OK";
    }
}
