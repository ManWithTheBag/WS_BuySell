package org.buysell.repository.productRepo;

import org.buysell.model.product.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
}
