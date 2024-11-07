package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.Advertisement;





public interface AdvertisementDAO extends GenericDAO<Advertisement, Integer> {
  
	List<Advertisement> findAll();
	






}


