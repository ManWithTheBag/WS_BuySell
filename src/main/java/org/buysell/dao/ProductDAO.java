package org.buysell.dao;

import jakarta.persistence.EntityManager;
import org.buysell.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product getById(long id) {
        Product product = entityManager.find(Product.class, id);

        return product;
    }

    public List<Product> getList() {
        List<Product> productList = entityManager.createQuery("FROM Product ", Product.class).getResultList();

        if (productList.size() == 0) {
            return null;
        }
        return productList;
    }

    public void create(Product product) {
        entityManager.persist(product);
    }

    public void remove(Product product) {
        entityManager.remove(product);
    }

}
