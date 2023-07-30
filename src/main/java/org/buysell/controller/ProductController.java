package org.buysell.controller;

import org.buysell.model.Product;
import org.buysell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "product/product";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product/productForm";
    }
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product) {

        System.out.println("Why id not correct?: " + product.toString());

        productService.save(product);
        return "redirect:/";
    }
    @PostMapping("/products/update")
    public String updateProductForm(@RequestParam("id") long id, Model model){
        Product product = productService.getById(id);
        model.addAttribute("product", product);

        return "product/productForm";
    }


    @PostMapping("/products/remove")
    public String removeProduct(@RequestParam("id") long id){
        productService.removeById(id);

        return "redirect:/";
    }


}
