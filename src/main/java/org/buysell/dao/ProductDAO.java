package org.buysell.dao;

import org.buysell.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private List<Product> productList;

    public ProductDAO(List<Product> productList) {
        this.productList = productList;

        createProductList();
    }

    public void createProductList(){
        productList.add(new Product(0, "Carrot", "A fresh Carrots",  (float) 2.46, "Stive Stivenson" ));
        productList.add(new Product(1, "Potato", "A fresh Potatos",  (float) 4.56, "Ben Bener" ));
        productList.add(new Product(2, "Cucumber", "A fresh Cucumbers",  (float) 3.8, "Jin Jones" ));
        productList.add(new Product(3, "Onion", "A fresh Onions",  (float) 6.6, "Jain Corn" ));
        productList.add(new Product(4, "Corn", "A fresh Corns",  (float) 23.54, "Inna Derfds" ));
    }

    public Product getById(long id){
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getList(){
        return productList;
    }
}
