package org.buysell.controller.product;

import org.buysell.model.product.Product;
import org.buysell.model.product.ProductComment;
import org.buysell.model.product.ProductImage;
import org.buysell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    private int maxSizeProductImageList = 5;
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String listEmployees(Model theModel) {
        List<Product> product = productService.getList();
        theModel.addAttribute("productList", product);

        return "mainPage";
//        return "test";
    }

    @GetMapping("/products")
    public String showProduct(@RequestParam("productId") long productId, Model model){
        model.addAttribute("product", productService.getById(productId));
        model.addAttribute("comment", new ProductComment());

        return "product/product";
    }

    @GetMapping("/products/new")
    public String showFormForAdd(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("preparedProductImageList", prepareProductImageList(new Product()));

        return "product/productForm";
    }

    @PostMapping ("/products/update")
    public String showFormForUpdate(@RequestParam("productId") long productId, Model model) {
        Product product = productService.getById(productId);
        model.addAttribute("product", product);
        List<ProductImage> testList =  prepareProductImageList(product);
        model.addAttribute("preparedProductImageList", testList);

        return "product/productForm";
    }

    @PostMapping("/products/save")
    public RedirectView saveProduct(RedirectAttributes attributes,
                                    @ModelAttribute("product") Product product,
                                    @RequestParam(value = "imageFile", required = false) MultipartFile[] imageFileArray,
                                    @RequestParam(value="resultImageId") Long[] requestedDbImageIdArray) throws IOException {

        List<MultipartFile> imageFileList = Arrays.stream(imageFileArray).toList();
        List<Long> requestedDbImageIdList = Arrays.stream(requestedDbImageIdArray).toList();

        Product savedProduct = productService.saveProduct(product, imageFileList, requestedDbImageIdList);

        attributes.addAttribute("productId", savedProduct.getId());

        return new RedirectView("/products");
    }
    @PostMapping("/products/remove")
    public String removeProduct(@RequestParam("productId") long productId){
        productService.removeById(productId);

        return "redirect:/";
    }

    @PostMapping("/products/search")
    public String getProductByTitle(@RequestParam("title") String title, Model model){
        List<Product> productList = productService.findByTitle(title);
        model.addAttribute("productList", productList);

        return "mainPage";
    }

    @PostMapping("/products/comment/new")
    public RedirectView addComment(RedirectAttributes attributes,
                                   @RequestParam("productId") long productId,
                                   @RequestParam("content") String content){
        Product product = productService.getById(productId);
        ProductComment productComment = new ProductComment();
        productComment.setContent(content);
        productComment.setProduct(product);
        productService.saveProductComment(productComment);

        attributes.addAttribute("productId", productId);
        return new RedirectView("/products");
    }


    private List<ProductImage> prepareProductImageList(Product product){
        List<ProductImage> preparedProductImageList;

        if(product.getProductImageList() != null) {
            preparedProductImageList = new ArrayList<>(product.getProductImageList());
            for (ProductImage p : preparedProductImageList) {
            }
        }else {
            preparedProductImageList = new ArrayList<>();
        }

        while (preparedProductImageList.size() < maxSizeProductImageList){
            preparedProductImageList.add(new ProductImage());
        }

        return preparedProductImageList;
    }

}
