package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.Transaction;





public interface TransactionDAO extends GenericDAO<Transaction, Integer> {
  
	List<Transaction> findAll();
	






}


