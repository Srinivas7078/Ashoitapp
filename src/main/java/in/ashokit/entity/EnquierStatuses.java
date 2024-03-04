package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Enquiry_statuses")
public class EnquierStatuses {
	@Id
    private Integer statusId;
    private String statusName;
}
