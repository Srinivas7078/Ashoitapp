package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="courses")
public class Course {
	@Id
    private Integer courseId;
    private String courseName;
    
}
