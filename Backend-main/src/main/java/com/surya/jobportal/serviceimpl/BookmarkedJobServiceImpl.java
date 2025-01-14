package com.surya.jobportal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surya.jobportal.dao.IBookmarkedJobDao;
import com.surya.jobportal.dao.IFreelancerDao;
import com.surya.jobportal.dao.IJobDao;
import com.surya.jobportal.dao.ISkillDao;
import com.surya.jobportal.dto.BookmarkedJobDTO;
import com.surya.jobportal.dto.BookmarkedJobListDTO;
import com.surya.jobportal.entities.BookmarkedJob;
import com.surya.jobportal.entities.Skill;
import com.surya.jobportal.exceptions.InvalidBookmarkedJobException;
import com.surya.jobportal.service.IBookmarkedJobService;


@Service
public class BookmarkedJobServiceImpl implements IBookmarkedJobService {
	@Autowired
	IBookmarkedJobDao bookmarkedjobdao;
	@Autowired
	ISkillDao skilldao;
	@Autowired
	IFreelancerDao freelancerdao;
	@Autowired
	IJobDao jobdao;
	
	
	/**
	 * 
	 * Method     : bookmarkedJob
	 * @param       bookmarkedjobdto
	 * @throws      InvalidBookmarkedJobException
	 * @return      BookmarkedJob object
	 * Description: The method saves the bookmarkedjob object.
	 * 
	 */
	
	@Transactional
	public BookmarkedJob bookmarkJob(BookmarkedJobDTO bookmarkedjobdto) 
	{
		BookmarkedJob bookmarkedJob=new BookmarkedJob();
	
		if (jobdao.existsById(bookmarkedjobdto.getJobId()) && 
				freelancerdao.existsById(bookmarkedjobdto.getFreelancerId())&&
				skilldao.existsById(bookmarkedjobdto.getSkillId())) {
			bookmarkedJob.setSkill(skilldao.findById(bookmarkedjobdto.getSkillId()).get());
			bookmarkedJob.setFreelancer(freelancerdao.findById(bookmarkedjobdto.getFreelancerId()).get());
			bookmarkedJob.setJob(jobdao.findById(bookmarkedjobdto.getJobId()).get());
		
		return bookmarkedjobdao.save(bookmarkedJob);
		}
		
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}
	/**
	 * 
	 * Method     : findBookmarkedJobsBySkillName
	 * @param       name
	 * @throws      InvalidBookmarkedJobException
	 * @return      List of BookmarkedJob
	 * Description: The method finds BookmarkedJob by Skill name, and returns a list.
	 * 
	 */
	@Override
	@Transactional
	public List<BookmarkedJob> findBookmarkedJobsBySkillName(String name) {
		if(skilldao.existsByName(name)) {
			Skill skill = skilldao.findByName(name);
			return bookmarkedjobdao.findBookmarkedJobBySkill(skill);
		}else throw new InvalidBookmarkedJobException();
	}
	
	/**
	 * 
	 * Method     : findById
	 * @param       id
	 * @throws      InvalidBookmarkedJobException
	 * @return      BookmarkedJob object
	 * Description: The method finds BookmarkedJob by their Id, and returns a list.
	 * 
	 */

	@Transactional
	@Override
	public BookmarkedJob findById(Long id) {
		if(bookmarkedjobdao.existsById(id))
		{
			
		return bookmarkedjobdao.findById(id).get();
		}
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}

	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Override
	public Long getCurrentId() {
		return bookmarkedjobdao.getCurrentSeriesId();
	}
	/**
	 * 
	 * Method     : remove
	 * @param       id
	 * @throws      InvalidBookmarkedJobException
	 * Description: The method finds BookmarkedJob by their Id, and remove it from the list.
	 * 
	 */
	
	@Transactional
	public void remove(Long BId)
	{
		if(bookmarkedjobdao.existsById(BId))
		{
			
		BookmarkedJob bj=bookmarkedjobdao.findById(BId).get();
		bookmarkedjobdao.delete(bj);
		}
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}
	
	
	@Override
	public List<BookmarkedJobListDTO> findAllBookmarks(Long frId) {
		return bookmarkedjobdao.findAllBookmarks(frId);
	}
	
	
	
	
	
	
	

}
