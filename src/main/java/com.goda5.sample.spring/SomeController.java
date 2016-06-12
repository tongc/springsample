package com.goda5.sample.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tong on 12/06/2016.
 */
@Controller
public class SomeController {
    @RequestMapping("/jsonp")
    public String jsonp() {
        return "html.html";
    }
}
