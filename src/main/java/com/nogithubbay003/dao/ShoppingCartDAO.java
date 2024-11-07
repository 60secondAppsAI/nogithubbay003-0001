package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.ShoppingCart;





public interface ShoppingCartDAO extends GenericDAO<ShoppingCart, Integer> {
  
	List<ShoppingCart> findAll();
	






}


