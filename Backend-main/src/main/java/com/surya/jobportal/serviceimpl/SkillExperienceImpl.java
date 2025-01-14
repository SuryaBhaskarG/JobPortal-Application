package com.surya.jobportal.serviceimpl;

import java.util.List;

/****************************************************
 * Method :SkillExperience
 * Method
 *@param skillExperienceDto
 * @throws IdNotFoundException
 * Description: This method creates a skillExperience for a particular skill for a freelancer
 ****************************************************/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surya.jobportal.dao.IFreelancerDao;
import com.surya.jobportal.dao.ISkillDao;
import com.surya.jobportal.dao.ISkillExperienceDao;
import com.surya.jobportal.dto.SkillExperienceDTO;
import com.surya.jobportal.dto.SkillExperienceListDTO;
import com.surya.jobportal.entities.SkillExperience;
import com.surya.jobportal.exceptions.InvalidSkillExperienceException;
import com.surya.jobportal.service.ISkillExperienceService;

@Service
@Transactional
public class SkillExperienceImpl implements ISkillExperienceService {

	@Autowired
	ISkillExperienceDao skillExperienceDao;
	@Autowired
	ISkillDao skillDao;
	@Autowired
	IFreelancerDao freelancerDao;

	@Override
	public SkillExperience addSkill(SkillExperienceDTO skillExperienceDto) {
		SkillExperience skillExperience = new SkillExperience();
		skillExperience.setYears(skillExperienceDto.getYears());
		skillExperience.setFreelancer(freelancerDao.findById(skillExperienceDto.getFreelancerId()).get());
		skillExperience.setSkill(skillDao.findById(skillExperienceDto.getSkillId()).get());
		return skillExperienceDao.save(skillExperience);
	}

	@Override
	public List<SkillExperienceListDTO> getSkillByFreelancerId(Long id) {
		if (freelancerDao.existsById(id)) {
			return skillExperienceDao.findByFreelancerId(id);
		} else
			throw new InvalidSkillExperienceException();
	}

	@Override
	public void updateSkillYears(Long id, Long freelancerId, Integer years) {
		if (skillExperienceDao.existsById(id)) {
			SkillExperience skillExperience = skillExperienceDao.findBySkillIdAndFreelancerId(id, freelancerId);
			skillExperience.setYears(years);
			skillExperienceDao.save(skillExperience);
		} else
			throw new InvalidSkillExperienceException();
	}

	@Override
	public SkillExperience getSkillById(Long id) {
		if (skillExperienceDao.existsById(id)) {
			return skillExperienceDao.findById(id).get();
		} else {
			throw new InvalidSkillExperienceException();
		}
	}

	@Override
	public List<SkillExperience> getByFreelancer(Long id) {
		if (freelancerDao.existsById(id)) {
			return skillExperienceDao.findByFreelancer(id);
		} else
			throw new InvalidSkillExperienceException();
	}
}