package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


