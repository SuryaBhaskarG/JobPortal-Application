package com.surya.jobportal.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surya.jobportal.dao.IFeedbackDao;
import com.surya.jobportal.dao.IFreelancerDao;
import com.surya.jobportal.dao.IRecruiterDao;
import com.surya.jobportal.dto.FeedbackDTO;
import com.surya.jobportal.dto.FeedbackListDTO;
import com.surya.jobportal.entities.Feedback;
import com.surya.jobportal.entities.Freelancer;
import com.surya.jobportal.entities.Recruiter;
import com.surya.jobportal.exceptions.InvalidFeedbackException;
import com.surya.jobportal.service.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	@Autowired
	IFeedbackDao feedbackDao;

	@Autowired
	IRecruiterDao recruiterDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Override
	public Float averageRating(String id) {
		if (freelancerDao.existsByUserName(id)) {
			return feedbackDao.averageRatings(id);
		}else throw new InvalidFeedbackException();
	}

	@Override
	public Feedback addFeedback(FeedbackDTO feedbackDto) {
		System.out.println(feedbackDto.toString());
		if (recruiterDao.existsByUserName(feedbackDto.getRecruiterUName())
				&& freelancerDao.existsByUserName(feedbackDto.getFreelancerUName())) {
			
			Recruiter recruiter = recruiterDao.findByUserName(feedbackDto.getRecruiterUName());
			Freelancer freelancer = freelancerDao.findByUserName(feedbackDto.getFreelancerUName());
			Feedback feedback = new Feedback();

			feedback.setComment(feedbackDto.getComments());
			feedback.setRanges(feedbackDto.getRanges());
			feedback.setCreatedBy(recruiter);
			feedback.setCreatedFor(freelancer);

			return feedbackDao.save(feedback);
		} else
			throw new InvalidFeedbackException();

	}

	@Override
	public List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId) {

		return feedbackDao.findFeedbacksForFreelancerByRecruiterId(fId, rId);
	}

}
