package poly.edu.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.edu.entity.Product;
import poly.edu.entity.Report;

public interface ProductDAO3 extends JpaRepository<Product, Integer> {

    @Query("""
        SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count
        FROM Product o
        GROUP BY o.category
        ORDER BY SUM(o.price) DESC
    """)
    List<Report> getInventoryByCategory();
}
