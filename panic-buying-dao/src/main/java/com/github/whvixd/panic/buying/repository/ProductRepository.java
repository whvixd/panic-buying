package com.github.whvixd.panic.buying.repository;

import com.github.whvixd.panic.buying.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);

    @Query(value = "select bean from product where productId=:productId")
    Product findByproductId(@Param("productId") String productId);


}
