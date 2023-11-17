package com.themindfulmountains.business.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BusinessController {
    //API Specs
    /*
        1. Add new business
        2. Get business by state/country/ location
        3. Update a business
     */

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
