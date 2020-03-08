package com.github.whvixd.panic.buying.repository;

import com.github.whvixd.panic.buying.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by wangzhx on 2020/3/1.
 */
@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);


}
