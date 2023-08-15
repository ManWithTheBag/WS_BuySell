package org.buysell.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.buysell.dao.ProductDAO;
import org.buysell.model.product.Product;
import org.buysell.model.product.ProductComment;
import org.buysell.model.product.ProductImage;
import org.buysell.repository.product.ProductCommentRepository;
import org.buysell.repository.product.ProductImageRepository;
import org.buysell.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private ProductDAO productDAO;
    private ProductRepository productRepository;
    private ProductCommentRepository commentRepository;
    private ProductImageRepository imageRepository;
    private ProductImageService imageService;

    @Autowired
    public ProductService(ProductDAO productDAO, ProductRepository productRepository, ProductImageService imageService,
                          ProductCommentRepository commentRepository, ProductImageRepository imageRepository) {
        this.productDAO = productDAO;
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

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

    public Product saveProduct(Product product, List<MultipartFile> imageFileList, List<Long> requestedDbImageIdList) throws IOException {
        Product savedProduct;
        product.initLocalDataTime();
        savedProduct = productRepository.save(product);

        imageService.saveProductImages(imageFileList, savedProduct, requestedDbImageIdList);

        ProductImage previewImage = imageService.setPreviewImageId(savedProduct);
        savedProduct.setPreviewImageID(previewImage.getImageId());
        savedProduct = productRepository.save(savedProduct);

        return savedProduct;
    }
}

