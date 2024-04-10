package com.surya.jobportal.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.FreelancerDTO;
import com.surya.jobportal.dto.FreelancerListDTO;
import com.surya.jobportal.entities.Freelancer;

/**************************************************************************************
 * @author       Vishnuvardhan 
 * Description : This is the Service Interface for Freelancer module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
public interface IFreelancerService {

	Freelancer findById(Long id);

	Long getCurrentId();

	Freelancer save(FreelancerDTO freelancerDto);

	Freelancer update(@Valid Long id, FreelancerDTO freelancerDto);

	Freelancer findByUserName(String userName);
	
	List<FreelancerListDTO> listFreelancers();
}
