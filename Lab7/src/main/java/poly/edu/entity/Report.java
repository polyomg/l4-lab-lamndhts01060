package poly.edu.entity;

import java.io.Serializable;

public interface Report {
    Serializable getGroup();  // Nhóm (ở đây là Category)
    Double getSum();          // Tổng giá trị sản phẩm
    Long getCount();          // Số lượng sản phẩm
}
