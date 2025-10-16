package poly.edu.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CookieService {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    // Đọc cookie từ request
    public Cookie get(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    // Đọc giá trị cookie
    public String getValue(String name) {
        Cookie cookie = get(name);
        return (cookie != null) ? cookie.getValue() : "";
    }

    // Thêm cookie mới
    public Cookie add(String name, String value, int hours) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(hours * 60 * 60); // đổi giờ sang giây
        response.addCookie(cookie);
        return cookie;
    }

    // Xóa cookie
    public void remove(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
