package org.buysell.repository.product;

import org.buysell.model.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    public void deleteById(long imageId);
    public List<ProductImage> findAllByProductId(long fk_productId);
}
