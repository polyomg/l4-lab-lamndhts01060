package poly.edu.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.edu.entity.Staff;

@Controller
public class StaffControllerBai2 {

    @RequestMapping("/staff/list")
    public String list(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("user1@gmail.com").fullname("nguyễn văn user1").salary(12345.68).photo("image.png").build(),
                Staff.builder().id("user2@gmail.com").fullname("nguyễn văn user2").salary(12345.68).photo("image.png").build(),
                Staff.builder().id("user3@gmail.com").fullname("nguyễn văn user3").salary(12345.68).photo("image.png").build(),
                Staff.builder().id("user4@gmail.com").fullname("nguyễn văn user4").salary(12345.68).photo("image.png").build(),
                Staff.builder().id("user5@gmail.com").fullname("nguyễn văn user5").salary(12345.68).photo("image.png").build(),
                Staff.builder().id("user6@gmail.com").fullname("nguyễn văn user6").salary(12345.68).photo("image.png").build()
        );

        model.addAttribute("list", list);
        return "staff/staff-list"; // trỏ tới file staff-list.html trong /templates/staff
    }
}