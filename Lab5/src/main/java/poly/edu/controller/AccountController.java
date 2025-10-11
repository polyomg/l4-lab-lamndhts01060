package poly.edu.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import poly.edu.service.CookieService;
import poly.edu.service.ParamService;
import poly.edu.service.SessionService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;

	// Hiển thị form login
	@GetMapping("/login")
	public String loginForm(Model model) {
		// Gợi nhớ username từ cookie (nếu có)
		String savedUser = cookieService.getValue("username");
		model.addAttribute("savedUser", savedUser);
		return "account/login";
	}

	// Xử lý login
	@PostMapping("/login")
	public String login(Model model) {
		// Đọc tham số từ form
		String username = paramService.getString("username", "");
		String password = paramService.getString("password", "");
		boolean remember = paramService.getBoolean("remember", false);

		// kiểm tra login (user: poly / 123)
		if (username.equals("poly") && password.equals("123")) {
			// Lưu user vào session
			sessionService.set("username", username);

			// chọn remember thì lưu cookie 10 ngày
			if (remember) {
				cookieService.add("username", username, 10 * 24); // 10 ngày
			} else {
				cookieService.remove("username");
			}

			model.addAttribute("message", "Đăng nhập thành công!");
		} else {
			model.addAttribute("message", "Sai username hoặc password!");
		}

		return "account/login";
	}
	
	@GetMapping("/register")
    public String register1() {
        return "/account/register";
    }

    @PostMapping("/register")
    public String register2(MultipartFile photo) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");

        File savedFile = paramService.save1(photo, "/images");

        if (savedFile != null) {
            System.out.println("Đăng ký thành công! Ảnh lưu tại: " + savedFile.getAbsolutePath());
        } else {
            System.out.println("Chưa chọn ảnh hoặc lưu thất bại!");
        }
        return "/account/login";
    }
}
