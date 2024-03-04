package in.ashokit.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.binding.EnquieryForm;
import in.ashokit.entity.StudentEnquieries;
import in.ashokit.entity.UserDtls;
import in.ashokit.repo.CourseRepo;
import in.ashokit.repo.EnquierStatusesRepo;
import in.ashokit.repo.StudentEnquieriesRepo;
import in.ashokit.repo.UserDtlsRepo;

@Service
public class EnquieryServiceImpl implements EnquieryServics {
	@Autowired
	private UserDtlsRepo userDtlsrepo;
	@Autowired
	private EnquierStatusesRepo statusRepo;
	@Autowired
	private CourseRepo courseRepo;
	@Autowired
	private StudentEnquieriesRepo EnquiryRepo;
	@Autowired
	private HttpSession session;

	@Override
	public DashBoardResponse dashBoard(Integer userId) {
		DashBoardResponse db=new DashBoardResponse();
		Optional<UserDtls> userDtls = userDtlsrepo.findById(userId);
		if (userDtls.isPresent()) {
			UserDtls entity = userDtls.get();
			List<StudentEnquieries> listOfEnq = entity.getEnquiries();
			Integer total = listOfEnq.size();
			Integer enrolled = listOfEnq.stream().filter(e -> e.getEnqStatus().equals("ENROLLED"))
			.collect(Collectors.toList()).size();
			Integer lost = listOfEnq.stream().filter(e -> e.getEnqStatus().equals("LOST"))
					.collect(Collectors.toList()).size();
			db.setToatalEnq(total);
			db.setEnrolledcnt(enrolled);
			db.setLostcnt(lost);
		}
		return db;
	}

	@Override
	public List<String> getCourses() {
		List<String> courses = courseRepo.getCourses();
		return courses;
	}

	@Override
	public List<String> getStatus() {
		List<String> status = statusRepo.getStatus();
		return status;
	}

	@Override
	public Boolean saveEnquiry(EnquieryForm form) {
		StudentEnquieries entity=new StudentEnquieries();
		BeanUtils.copyProperties(form, entity);
		Integer userId = (Integer)session.getAttribute("userId");
		UserDtls userDtls = userDtlsrepo.findById(userId).get();
		entity.setUser(userDtls);
		StudentEnquieries save = EnquiryRepo.save(entity);
		if(save!=null) {
			return true;
		}
		return false;
	}

}
