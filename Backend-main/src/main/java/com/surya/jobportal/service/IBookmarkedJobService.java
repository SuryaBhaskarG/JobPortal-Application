package com.surya.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.surya.jobportal.dto.BookmarkedJobDTO;
import com.surya.jobportal.dto.BookmarkedJobListDTO;
import com.surya.jobportal.entities.BookmarkedJob;



@Service
public interface IBookmarkedJobService {

	BookmarkedJob bookmarkJob(BookmarkedJobDTO bjd);

	List<BookmarkedJob> findBookmarkedJobsBySkillName(String name);

	BookmarkedJob findById(Long id);
	
	List<BookmarkedJobListDTO> findAllBookmarks(Long frId);

	Long getCurrentId();
	
	void remove(Long BId);

}
