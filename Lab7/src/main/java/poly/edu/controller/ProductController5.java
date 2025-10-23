package poly.edu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.dao.ProductDAO5;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;

@Controller
public class ProductController5 {

    @Autowired
    ProductDAO5 dao;

    @Autowired
    SessionService session;

    @RequestMapping("/product/search-and-page-dsl")
    public String searchAndPageDSL(Model model,
                                   @RequestParam("keywords") Optional<String> kw,
                                   @RequestParam("p") Optional<Integer> p) {

        // 🔹 Lấy từ khóa (nếu không có thì lấy trong session)
        String keywords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", keywords);

        // 🔹 Cấu hình phân trang: mỗi trang có 5 sản phẩm
        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        // 🔹 Gọi DAO bằng DSL
        Page<Product> page = dao.findAllByNameLike("%" + keywords + "%", pageable);

        // 🔹 Gửi dữ liệu sang view
        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);

        return "product/search-and-page-dsl";
    }
}
