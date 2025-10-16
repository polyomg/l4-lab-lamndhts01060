package poly.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;

@Controller
public class ProductController {

    @Autowired
    ProductDAO dao;

    @RequestMapping("/product/sort")
    public String sort(Model model,
                       @RequestParam("field") Optional<String> field) {

        // Nếu chưa chọn cột -> mặc định sắp theo "price"
        String sortField = field.orElse("price");
        Sort sort = Sort.by(Direction.DESC, sortField);

        // Truy vấn dữ liệu đã sắp xếp
        List<Product> items = dao.findAll(sort);

        // Gửi dữ liệu qua view
        model.addAttribute("field", sortField.toUpperCase());
        model.addAttribute("items", items);

        return "product/sort";
    }
}
