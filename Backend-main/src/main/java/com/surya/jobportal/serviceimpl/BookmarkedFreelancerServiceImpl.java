package com.surya.jobportal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.surya.jobportal.dao.IBookmarkedFreelancerDao;
import com.surya.jobportal.dao.IFreelancerDao;
import com.surya.jobportal.dao.IRecruiterDao;
import com.surya.jobportal.dao.ISkillDao;
import com.surya.jobportal.dto.BookmarkedFreelancerDTO;
import com.surya.jobportal.dto.BookmarkedFreelancerListDTO;
import com.surya.jobportal.entities.BookmarkedFreelancer;
import com.surya.jobportal.entities.Skill;
import com.surya.jobportal.exceptions.InvalidBookmarkedFreelancerException;
import com.surya.jobportal.service.IBookmarkedFreelancerService;

/**************************************************************************************
 * @author       Vishnuvardhan 
 * Description : This is the Service Implementation for BookmarkedFreelancer module.	 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
@Transactional
public class BookmarkedFreelancerServiceImpl implements IBookmarkedFreelancerService {

	@Autowired
	IBookmarkedFreelancerDao bookmarkedFreelancerDao;

	@Autowired
	ISkillDao skillDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Autowired
	IRecruiterDao recruiterDao;

	/*****************************************************************************
	 * Method     : bookmarkFreelancer
	 * @param       bookmarkFreelancerDto
	 * @throws      InvalidBookmarkedFreelancerException  
	 * @return      BookmarkedFreelancer object
	 * Description: This method creates a bookmark of an existing freelancer. 
	 * 				The recruiter can create a bookmark of the freelancer.      
	 ****************************************************************************/
	@Override
	public BookmarkedFreelancer bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto) {

		BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();

		if (recruiterDao.existsById(bookmarkedFreelancerDto.getRecruiterId())
				&& freelancerDao.existsById(bookmarkedFreelancerDto.getFreelancerId())) {

			bookmarkedFreelancer.setBookmarkedBy(recruiterDao.findById(bookmarkedFreelancerDto.getRecruiterId()).get());
			bookmarkedFreelancer.setFreelancer(freelancerDao.findById(bookmarkedFreelancerDto.getFreelancerId()).get());

			return bookmarkedFreelancerDao.save(bookmarkedFreelancer);
		} else
			throw new InvalidBookmarkedFreelancerException();

	}

	/*************************************************************
	 * Method     : deleteBookmarkedFreelancerById
	 * @param       id
	 * @throws      InvalidBookmarkedFreelancerException
	 * @return      void
	 * Description: This method deletes a Bookmarked Freelancer 
	 *              if it exists by the given Id.
	 ************************************************************/
	@Override
	public void deleteBookmarkedFreelancerById(Long id) {
		if (bookmarkedFreelancerDao.existsById(id)) {
			bookmarkedFreelancerDao.deleteById(id);
		} else {
			throw new InvalidBookmarkedFreelancerException();
		}

	}

	
	/**************************************************************************************
	 * Method     : findById
	 * @param       id
	 * @throws      InvalidBookmarkedFreelancerException
	 * @return      BookmarkedFreelancer object
	 * Description: The method finds BookmarkedFreelancers by their Id, and returns a list.
	 *************************************************************************************/
	@Override
	public BookmarkedFreelancer findById(Long id) {
		if (bookmarkedFreelancerDao.existsById(id)) {
			return bookmarkedFreelancerDao.findById(id).get();
		} else
			throw new InvalidBookmarkedFreelancerException();

	}

	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Override
	public Long getCurrentId() {
		return bookmarkedFreelancerDao.getCurrentSeriesId();
	}

	@Override
	public BookmarkedFreelancer save(BookmarkedFreelancerDTO bookmarkedFreelancerDto) {
		BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();
		bookmarkedFreelancer.setBookmarkedBy(recruiterDao.findById(bookmarkedFreelancerDto.getRecruiterId()).get());
		bookmarkedFreelancer.setFreelancer(freelancerDao.findById(bookmarkedFreelancerDto.getFreelancerId()).get());
		return bookmarkedFreelancerDao.save(bookmarkedFreelancer);
	}

	@Override
	public List<BookmarkedFreelancerListDTO> getAll() {
		return bookmarkedFreelancerDao.findAllDTO();
	}

}
