package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.Subscription;





public interface SubscriptionDAO extends GenericDAO<Subscription, Integer> {
  
	List<Subscription> findAll();
	






}


