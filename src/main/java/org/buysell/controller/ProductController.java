package org.buysell.controller;

import org.buysell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("productList", productService.getList());

        return "mainPage";
    }
    @GetMapping("/products/{id}")
    public String productById(Model model, @PathVariable long id){


        if(productService.getById(id) == null){
            model.addAttribute("searchId", id);
            return "error/errorNotFindProduct";
        }

        model.addAttribute("product", productService.getById(id));

        return "product";
    }


}
