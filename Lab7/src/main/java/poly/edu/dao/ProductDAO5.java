package poly.edu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import poly.edu.entity.Product;

public interface ProductDAO5 extends JpaRepository<Product, Integer> {

    // 🔹 Dùng DSL thay cho @Query
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
