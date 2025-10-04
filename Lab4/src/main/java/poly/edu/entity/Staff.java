package poly.edu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
	private String id;

    private String fullname;

    @Builder.Default
    private String photo = "image.png";

    private Boolean gender;

    @Builder.Default
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday = new Date();

    private String email;

    @Builder.Default
    private double salary = 12345.6789;

    @Builder.Default
    private Integer love = 0;
    
    @Builder.Default
    private Integer level = 0; 
}
