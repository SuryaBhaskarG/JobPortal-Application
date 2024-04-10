package com.surya.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.BookmarkedFreelancerDTO;
import com.surya.jobportal.dto.BookmarkedFreelancerListDTO;
import com.surya.jobportal.entities.BookmarkedFreelancer;

/**************************************************************************************
 * @author       Vishnuvardhan 
 * Description : This is the Service Interface for BookmarkedFreelancer module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
public interface IBookmarkedFreelancerService {

	BookmarkedFreelancer bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

	void deleteBookmarkedFreelancerById(Long id);

	BookmarkedFreelancer findById(Long id);
	
	List<BookmarkedFreelancerListDTO> getAll();

	Long getCurrentId();

	BookmarkedFreelancer save(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

}
