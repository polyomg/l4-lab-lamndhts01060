package poly.edu.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import java.io.ObjectInputStream.GetField;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SessionService {
	@Autowired
	HttpSession session;
	
	// Đọc giá trị trong session
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}
	
	// Ghi giá trị vào session
    public void set(String name, Object value) {
    	session.setAttribute(name, value);
    }
    
 // Xóa attribute khỏi session
    public void remove(String name) {
    	session.removeAttribute(name);
    }
}
