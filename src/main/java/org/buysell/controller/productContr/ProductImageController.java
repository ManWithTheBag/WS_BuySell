package org.buysell.controller.productContr;

import lombok.AllArgsConstructor;
import org.buysell.model.product.ProductImage;
import org.buysell.repository.productRepo.ProductImageRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayInputStream;

@Controller
@AllArgsConstructor
public class ProductImageController {
    private ProductImageRepository imageRepository;

    @GetMapping("/images/{imageId}")
    @ResponseBody
    public ResponseEntity<?> getProductImageById(@PathVariable("imageId") long imageId){
        ProductImage productImage = imageRepository.findById(imageId).orElse(null);
        return ResponseEntity.ok()
                .header(productImage.getName())
                .contentType(MediaType.valueOf(productImage.getContentType()))
                .contentLength(productImage.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(productImage.getImageByte())));
    }

    @PostMapping("/images/remove")
    public RedirectView removeProductImage(RedirectAttributes attributes,
                                           @RequestParam("productId") long productId,
                                           @RequestParam("imageId") long imageId){
        imageRepository.deleteById(imageId);
        attributes.addAttribute("productId", productId);
        System.out.println("Remove");

        return new RedirectView("/products/update");
    }
}
