package org.buysell.service;

import jakarta.transaction.Transactional;
import org.buysell.dao.ProductDAO;
import org.buysell.model.Product;
import org.buysell.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.PropertyPermission;

@Service
@Transactional
public class ProductService {

    private ProductDAO productDAO;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductDAO productDAO, ProductRepository productRepository) {
        this.productDAO = productDAO; this.productRepository = productRepository;
    }

    public Product getById(long id){
        return productDAO.getById(id);
    }

    public Product findByTitle(String title){
        return productRepository.findByTitle(title);
    }

    public List<Product> getList(){
        return productDAO.getList();
    }

//    public Product update(Product product){
//        return productRepository.save(product);
//    }

    public void create(Product product){
        productDAO.create(product);
    }

    public void removeById(long id){
        Product product = getById(id);
        productDAO.remove(product);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }
}
