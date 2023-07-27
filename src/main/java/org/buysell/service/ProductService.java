package org.buysell.service;

import org.buysell.dao.ProductDAO;
import org.buysell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void createProducts(){
        productDAO.createProductList();
    }

    public Product getById(long id){
        return productDAO.getById(id);
    }

    public List<Product> getList(){
        return productDAO.getList();
    }
}
