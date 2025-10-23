package poly.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    // Đọc attribute từ session
    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }

    // Đọc attribute, nếu null thì trả về defaultValue
    public <T> T get(String name, T defaultValue) {
        T value = get(name);
        return (value != null) ? value : defaultValue;
    }

    // Ghi attribute vào session
    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    // Xóa attribute
    public void remove(String name) {
        session.removeAttribute(name);
    }
}
