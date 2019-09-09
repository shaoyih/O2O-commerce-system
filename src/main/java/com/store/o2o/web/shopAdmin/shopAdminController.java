package com.store.o2o.web.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shopadmin")
public class shopAdminController {

    @GetMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }
    @GetMapping(value = "/shoplist")
    public String shopList() {
        return "shop/shoplist";

    }
    @GetMapping(value = "/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }

    @GetMapping(value = "/productcategorymanagement")
    private String productCategoryManage() {
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement() {
        return "shop/productmanagement";
    }


}
