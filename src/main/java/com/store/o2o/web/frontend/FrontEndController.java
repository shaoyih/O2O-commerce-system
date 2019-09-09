package com.store.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontEndController {
    @GetMapping(value = "/index")
    private String index() {
        return "frontend/index";
    }
    @GetMapping(value = "/shoplist")
    private String showShopList() {

        return "frontend/shoplist";
    }
    @GetMapping(value = "/productdetail")
    private String showProductDetail() {

        return "frontend/productdetail";
    }
    @GetMapping(value = "/shopdetail")
    private String showShopDetail() {

        return "frontend/shopdetail";
    }


}
