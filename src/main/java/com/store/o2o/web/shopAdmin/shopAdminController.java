package com.store.o2o.web.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("shopadmin")
public class shopAdminController {

    @GetMapping(value = "/shopoperation")
    public String shopOperation(){
        System.out.println("testt");
        return "shopOperation";
    }
}
