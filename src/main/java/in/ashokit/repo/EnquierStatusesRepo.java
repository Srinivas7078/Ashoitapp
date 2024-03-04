package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.EnquierStatuses;

public interface EnquierStatusesRepo extends JpaRepository<EnquierStatuses, Integer> {
     @Query("select distinct(statusName) from EnquierStatuses")
	public List<String> getStatus();
}
