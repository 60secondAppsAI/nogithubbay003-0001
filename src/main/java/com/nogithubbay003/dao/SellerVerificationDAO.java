package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.SellerVerification;





public interface SellerVerificationDAO extends GenericDAO<SellerVerification, Integer> {
  
	List<SellerVerification> findAll();
	






}


