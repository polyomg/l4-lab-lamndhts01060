package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import poly.edu.entity.StaffBai2;

@Controller
public class StaffControllerBai2 {

    @RequestMapping("/staff/create/formBai2")
    public String createForm(Model model, @ModelAttribute("staff") StaffBai2 staff) {
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "staff/staff-validate";
    }

    @RequestMapping("/staff/create/saveBai2")
    public String createSave(Model model,
                             @Valid @ModelAttribute("staff") StaffBai2 staff,
                             org.springframework.validation.BindingResult errors, // SỬA LẠI DÒNG NÀY
                             @RequestParam("photo_file") MultipartFile photoFile) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau!");
            return "staff/staff-validate";
        }

        // Xử lý upload ảnh chỉ khi không có lỗi
        if (!photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }

        model.addAttribute("message", "Dữ liệu đã nhập đúng!");
        return "staff/staff-validate";
    }
}
