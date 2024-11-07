package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.Feedback;





public interface FeedbackDAO extends GenericDAO<Feedback, Integer> {
  
	List<Feedback> findAll();
	






}


