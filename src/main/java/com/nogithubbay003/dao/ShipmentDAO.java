package com.nogithubbay003.dao;

import java.util.List;

import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.domain.Shipment;





public interface ShipmentDAO extends GenericDAO<Shipment, Integer> {
  
	List<Shipment> findAll();
	






}


