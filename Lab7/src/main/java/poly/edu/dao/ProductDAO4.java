package poly.edu.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import poly.edu.entity.Product;

public interface ProductDAO4 extends JpaRepository<Product, Integer> {
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
