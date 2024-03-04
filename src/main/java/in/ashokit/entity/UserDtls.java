package in.ashokit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="User_Dtls")
public class UserDtls {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
     private Integer userId;
     private String userName;
     private String email; 
     private Long phNum;
     private String pwd;
     private String accStatus;
     @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
     private List<StudentEnquieries> enquiries;
}
