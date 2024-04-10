package com.surya.jobportal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surya.jobportal.dao.ISkillDao;
import com.surya.jobportal.dto.SkillDTO;
import com.surya.jobportal.entities.Skill;
import com.surya.jobportal.exceptions.DuplicateSkillException;
import com.surya.jobportal.exceptions.InvalidSkillException;
import com.surya.jobportal.service.ISkillService;

/**************************************************************************************
 * @author       Vishnuvardhan 
 * Description : This is the Service Implementation for Skill module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
@Transactional
public class SkillServiceImpl implements ISkillService {

	@Autowired
	ISkillDao skillDao;

	@Override
	public Skill findById(Long id) {
		return skillDao.findById(id).get();
	}

	@Override
	public List<Skill> getAllSkills() {
		return skillDao.findAll();
	}

	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Override
	public Long getCurrentId() {
		return skillDao.getCurrentSeriesId();
	}

	@Override
	public Skill getSkill(Long id) {
		return skillDao.getOne(id);
	}

	@Override
	public String remove(Long id) {
		if (skillDao.existsById(id)) {
			skillDao.deleteById(id);
			return "Deleted";
		} else {
			throw new InvalidSkillException();
		}
	}

	@Override
	public Skill save(Skill skill) {
		return skillDao.save(skill);
	}

	public Skill save(SkillDTO skillDto) {
		Skill skill = new Skill();
		if (skillDao.existsByName(skillDto.getName())) {
			throw new DuplicateSkillException();
		} else {
			skill.setName(skillDto.getName());
			skill.setDescription(skillDto.getDescription());
			return skillDao.save(skill);
		}
	}

	@Override
	public Skill update(Long id, Skill skill) {
		if (skillDao.existsById(id)) {
			return skillDao.save(skill);
		} else {
			throw new InvalidSkillException();
		}
	}

}
