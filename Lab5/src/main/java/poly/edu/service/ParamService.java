package poly.edu.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    // Đọc chuỗi
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        return (value != null) ? value : defaultValue;
    }

    // Đọc số nguyên
    public int getInt(String name, int defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Integer.parseInt(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Đọc số thực
    public double getDouble(String name, double defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Double.parseDouble(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Đọc boolean
    public boolean getBoolean(String name, boolean defaultValue) {
        try {
            String value = request.getParameter(name);
            return (value != null) ? Boolean.parseBoolean(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Đọc ngày tháng
    public Date getDate(String name, String pattern) {
        try {
            String value = request.getParameter(name);
            if (value == null) return null;
            return new SimpleDateFormat(pattern).parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Sai định dạng ngày: " + pattern, e);
        }
    }

    // Lưu file upload
    public File save(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            try {
                File dir = new File(request.getServletContext().getRealPath(path));
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File savedFile = new File(dir, file.getOriginalFilename());
                file.transferTo(savedFile);
                return savedFile;
            } catch (IOException e) {
                throw new RuntimeException("Lỗi lưu file", e);
            }
        }
        return null;
    }
    
    public File save1(MultipartFile file, String path) {
	    try {
	        if (!file.isEmpty()) {
	            // Lấy đường dẫn tuyệt đối tới thư mục static/images
	            String basePath = System.getProperty("user.dir") 
	                    + "/src/main/resources/static" + path;

	            File dir = new File(basePath);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            File savedFile = new File(dir, file.getOriginalFilename());
	            file.transferTo(savedFile);
	            return savedFile;
	        }
	        return null;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
