package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


