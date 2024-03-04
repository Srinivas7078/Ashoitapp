package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.UserDtls;

public interface UserDtlsRepo extends JpaRepository<UserDtls, Integer> {
	public UserDtls findByEmail(String email);
	
	
}
