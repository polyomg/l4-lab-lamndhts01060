package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.dao.ProductDAO;
import poly.edu.dao.CategoryDAO;
import poly.edu.entity.Product;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductCRUDController {

    @Autowired
    ProductDAO productDAO;
    @Autowired
    CategoryDAO categoryDAO;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("item", new Product());
        model.addAttribute("items", productDAO.findAll());
        model.addAttribute("categories", categoryDAO.findAll());
        return "product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product item = productDAO.findById(id).orElse(new Product());
        model.addAttribute("item", item);
        model.addAttribute("items", productDAO.findAll());
        model.addAttribute("categories", categoryDAO.findAll());
        return "product/index";
    }

    @PostMapping("/create")
    public String create(Product item) {
        productDAO.save(item);
        return "redirect:/product/index";
    }

    @PostMapping("/update")
    public String update(Product item) {
        productDAO.save(item);
        return "redirect:/product/edit/" + item.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productDAO.deleteById(id);
        return "redirect:/product/index";
    }
}
