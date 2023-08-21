package org.buysell.service.productService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.buysell.dao.ProductDAO;
import org.buysell.model.product.Product;
import org.buysell.model.product.ProductComment;
import org.buysell.model.product.ProductImage;
import org.buysell.repository.productRepo.ProductCommentRepository;
import org.buysell.repository.productRepo.ProductImageRepository;
import org.buysell.repository.productRepo.ProductRepository;
import org.buysell.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ProductCommentRepository commentRepository;
    private ProductImageService imageService;
    private UserService userService;

    public Product getById(long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public List<Product> getList() {
        return productRepository.findAll();
    }

    public void removeById(long id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    public ProductComment saveProductComment(ProductComment productComment) {
        return commentRepository.save(productComment);
    }

    public Product saveProduct(Product product, List<MultipartFile> imageFileList, List<Long> requestedDbImageIdList, Principal principal) throws IOException {
        Product savedProduct;
        product.initLocalDataTime();
        product.setUser(userService.getUserByPrincipal(principal));
        savedProduct = productRepository.save(product);

        imageService.saveProductImages(imageFileList, savedProduct, requestedDbImageIdList);

        ProductImage previewImage = imageService.setPreviewImageId(savedProduct);
        savedProduct.setPreviewImageID(previewImage.getImageId());
        savedProduct = productRepository.save(savedProduct);

        return savedProduct;
    }

}

