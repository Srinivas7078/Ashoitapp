package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.binding.EnquieryForm;
import in.ashokit.binding.EnquierySearchCriteria;

public interface EnquieryServics {
	

	public DashBoardResponse dashBoard(Integer userId);
    
	public List<String> getCourses();
	public List<String> getStatus();
	
	public Boolean saveEnquiry(EnquieryForm form);
	
}
