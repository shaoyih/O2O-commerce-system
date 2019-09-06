package com.store.o2o.web.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.o2o.dto.ImageHolder;
import com.store.o2o.dto.ShopExecution;
import com.store.o2o.entity.Area;
import com.store.o2o.entity.PersonInfo;
import com.store.o2o.entity.Shop;
import com.store.o2o.entity.ShopCategory;
import com.store.o2o.enums.ShopStateEnum;
import com.store.o2o.service.AreaService;
import com.store.o2o.service.ShopCategoryService;
import com.store.o2o.service.ShopService;
import com.store.o2o.util.CodeUtil;
import com.store.o2o.util.HttpServletRequestUtil;
import com.store.o2o.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    private ShopService shopService;
    private ShopCategoryService shopCategoryService;
    private AreaService areaService;


    public ShopManagementController(ShopService shopService, ShopCategoryService shopCategoryService, AreaService areaService) {
        this.shopService = shopService;
        this.shopCategoryService = shopCategoryService;
        this.areaService = areaService;
    }

    @GetMapping("/getshopmanagementinfo")
    @ResponseBody
    private Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> modelMap= new HashMap<>();
        long shopId= HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId<=0){
            Object currentShopObj= request.getSession().getAttribute("currentShop");
            if(currentShopObj==null){
                modelMap.put("redirect",true);
                modelMap.put("url","/o2o/shop/shoplist");
            }else{
                Shop currentShop= (Shop)currentShopObj;
                modelMap.put("redirect",false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        }else{
            Shop currentShop= new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",currentShop);
            modelMap.put("redirect",false);
        }
        return modelMap;
    }

    @GetMapping(value="/getshoplist")
    @ResponseBody
    private Map<String, Object> getShopList(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        PersonInfo user=new PersonInfo();
        user.setUserId(1L);
        user.setName("小黄");
        request.getSession().setAttribute("user",user);
        user= (PersonInfo) request.getSession().getAttribute("user");
        List<Shop> shopList= new ArrayList<>();
        try {
            Shop shopCondition= new Shop();
            shopCondition.setOwner(user);
            ShopExecution se= shopService.getShopList(shopCondition,0,100);
            modelMap.put("shopList",se.getShopList());
            modelMap.put("user",user);
            modelMap.put("success",true);

        }catch (Exception e){
            modelMap.put("scuess",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }


    @GetMapping(value = "/getshopbyid")
    @ResponseBody
    private  Map<String, Object> getShopById(HttpServletRequest request){
        Map<String,Object> modelMap= new HashMap<>();
        Long shopId=HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId>-1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
            }

        }else{
            modelMap.put("success", false);
            modelMap.put("errMsg","Empty shopId");
        }
        return modelMap;
    }

    @GetMapping(value = "/getshopinitinfo")
    @ResponseBody
    private Map<String,Object> getShopInfo(){
        Map<String, Object> modelMap= new HashMap<>();
        List<ShopCategory> shopCategoryList=new ArrayList<ShopCategory>();
        List<Area> areaList=new ArrayList<>();
        try{
            shopCategoryList=shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList=areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;

    }

    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "wrong verification code");
            return modelMap;
        }
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = ((CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg"));
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "image cannot be empty");
            return modelMap;
        }
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            //temporally use hard code user, add session later
            shop.setOwner(owner);


            ShopExecution se = null;
            try {
                ImageHolder imageHolder= new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
                se = shopService.addShop(shop,imageHolder );
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modelMap.put("success", true);
                    // The store user can modify
                    List<Shop> shopList= (List<Shop>) request.getSession().getAttribute("shopList");
                    if(shopList==null || shopList.size()==0){
                        shopList=new ArrayList<>();
                    }
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList",shopList);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "input store info");
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "image cannot be empty");
            }

            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "please enter shop info");
            return modelMap;
        }
    }

        @PostMapping(value = "/modifyshop")
        @ResponseBody
        private Map<String, Object> modifyShop(HttpServletRequest request){
            Map<String,Object> modelMap =new HashMap<String,Object>();
            if(!CodeUtil.checkVerifyCode(request)){
                modelMap.put("success",false);
                modelMap.put("errMsg","wrong verification code");
                return modelMap;
            }
            String shopStr= HttpServletRequestUtil.getString(request,"shopStr");
            ObjectMapper mapper=new ObjectMapper();
            Shop shop=null;
            try {
                shop=mapper.readValue(shopStr,Shop.class);
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
            CommonsMultipartFile shopImg=null;
            CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            if(commonsMultipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;
                shopImg=((CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg"));
            }

            // modify shop info
            if(shop!=null && shop.getShopId()!=null){

                ShopExecution se= null;
                try {
                    if(shopImg==null){
                        se = shopService.modifyShop(shop,null);
                    }else{
                        ImageHolder imageHolder= new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
                        se = shopService.modifyShop(shop,imageHolder);
                    }
                    if(se.getState()== ShopStateEnum.SUCCESS.getState()){
                        modelMap.put("success",true);
                    }else{
                        modelMap.put("success",false);
                        modelMap.put("errMsg","input store info");
                    }
                } catch (IOException e) {
                    modelMap.put("success",false);
                    modelMap.put("errMsg","image cannot be empty");
                }

                return modelMap;
            }
            else{
                modelMap.put("success",false);
                modelMap.put("errMsg","please enter shop id");
                return modelMap;
            }

    }

//    private static void inputStreamToFile(InputStream ins, File file){
//        FileOutputStream os=null;
//        try{
//            os=new FileOutputStream(file);
//            int bytesRead=0;
//            byte[] buffer= new byte[1024];
//            while((bytesRead=ins.read(buffer))!=-1){
//                os.write(buffer,0,bytesRead);
//            }
//        } catch (Exception e){
//            throw new RuntimeException("get inputStreamTofile has problem: "+e.getMessage());
//        }finally {
//            try{
//                if(os!=null)os.close();
//                if(ins!=null)ins.close();
//            }catch (IOException e){
//                throw new RuntimeException("inputStreamTo file closing file problem: "+e.getMessage());
//            }
//        }
//    }

}
