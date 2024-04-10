package com.surya.jobportal.service;

import java.util.List;

import com.surya.jobportal.dto.SkillExperienceDTO;
import com.surya.jobportal.dto.SkillExperienceListDTO;
import com.surya.jobportal.entities.SkillExperience;


/**************************************************************************************
 * @author       Amruth N 
 * Description : This is the Service Interface for SkillExperience module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
public interface ISkillExperienceService {

	public SkillExperience addSkill(SkillExperienceDTO skillExperienceDto);

	public List<SkillExperienceListDTO> getSkillByFreelancerId(Long id);
	
	public SkillExperience getSkillById(Long id);
	
	public List<SkillExperience> getByFreelancer(Long id);

	public void updateSkillYears(Long id, Long freelancerId, Integer years);
}