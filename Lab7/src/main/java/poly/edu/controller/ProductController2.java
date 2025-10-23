package poly.edu.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.dao.ProductDAO2;
import poly.edu.entity.Product;
import poly.edu.service.SessionService;

@Controller
public class ProductController2 {

    @Autowired
    ProductDAO2 dao;

    @Autowired
    SessionService session;

    @RequestMapping("/product/search-and-page")
    public String searchAndPage(Model model,
                                @RequestParam("keywords") Optional<String> kw,
                                @RequestParam("p") Optional<Integer> p) {

        // Lấy từ khóa tìm kiếm (nếu không có thì lấy từ session)
        String keywords = kw.orElse(session.get("keywords", ""));
        session.set("keywords", keywords);

        // Cấu hình phân trang (5 sản phẩm / trang)
        Pageable pageable = PageRequest.of(p.orElse(0), 5);

        // Truy vấn dữ liệu theo từ khóa
        Page<Product> page = dao.findByKeywords("%" + keywords + "%", pageable);

        // Gửi dữ liệu sang view
        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);

        return "product/search-and-page";
    }
}