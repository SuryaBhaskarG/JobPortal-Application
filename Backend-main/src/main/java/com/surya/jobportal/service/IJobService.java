package com.surya.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.JobDTO;
import com.surya.jobportal.entities.Job;

@Service
public interface IJobService {

	void close(Long id);

	Job findById(Long id);
	
	List<Object> findJobsBySkillName(String name);
	
	List<Object> findAll();
	
	List<Object> findAllActiveJobs();
	
	Job postJob(JobDTO jobDto);
	
	void awardJob(Long jobId, Long freelancerId);
}
