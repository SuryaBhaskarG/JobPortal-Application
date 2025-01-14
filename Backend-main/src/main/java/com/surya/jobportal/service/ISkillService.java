package com.surya.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.SkillDTO;
import com.surya.jobportal.entities.Skill;

/**************************************************************************************
 * @author       Amruth N 
 * Description : This is the Service Interface for SkillService module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
public interface ISkillService {

	Skill findById(Long id);

	List<Skill> getAllSkills();

	Long getCurrentId();

	Skill getSkill(Long id);

	String remove(Long id);

	Skill save(Skill skill);

	Skill save(SkillDTO skillDto);

	Skill update(Long id, Skill skill);

}
