package com.goda5.sample.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.togglz.core.spi.FeatureProvider;

/**
 * Created by tong on 12/06/2016.
 */
@Controller
public class SomeController {
    private final FeatureProvider featureProvider;
    @Autowired
    public SomeController(FeatureProvider featureProvider) {
        this.featureProvider = featureProvider;
    }

    @RequestMapping("/jsonp")
    public String jsonp() {
        System.out.println(featureProvider.getFeatures());
        return "html.html";
    }
}
