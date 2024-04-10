package com.surya.jobportal.service;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.AdminDTO;
import com.surya.jobportal.entities.Admin;

@Service
public interface IAdminService {
	Admin findById(Long id);

	Admin save(AdminDTO adminDto);

	Admin update(Long id, AdminDTO adminDto);
	
	Admin findByUserName(String userName);

}
