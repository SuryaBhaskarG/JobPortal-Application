package com.surya.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.FeedbackDTO;
import com.surya.jobportal.dto.FeedbackListDTO;
import com.surya.jobportal.entities.Feedback;

@Service
public interface IFeedbackService {

	Float averageRating(String id);

	Feedback addFeedback(FeedbackDTO feedbackDto);

	List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiter(String fId, String rId);
}
