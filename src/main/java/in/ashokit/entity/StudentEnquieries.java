package in.ashokit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="Stusnet_EnqList")
public class StudentEnquieries {
	@Id
	@GeneratedValue
    private Integer EnquiryId;
    private String studentName;
    private Long studentPhNum;
    private String clsMode;
    private String courseName;
    private String enqStatus;
    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updatedDate;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDtls user;
//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
//    private List<StudentEnquieries> enquiries;
}
