package com.nogithubbay003.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.nogithubbay003.domain.Shipment;
import com.nogithubbay003.dto.ShipmentDTO;
import com.nogithubbay003.dto.ShipmentSearchDTO;
import com.nogithubbay003.dto.ShipmentPageDTO;
import com.nogithubbay003.dto.ShipmentConvertCriteriaDTO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShipmentService extends GenericService<Shipment, Integer> {

	List<Shipment> findAll();

	ResultDTO addShipment(ShipmentDTO shipmentDTO, RequestDTO requestDTO);

	ResultDTO updateShipment(ShipmentDTO shipmentDTO, RequestDTO requestDTO);

    Page<Shipment> getAllShipments(Pageable pageable);

    Page<Shipment> getAllShipments(Specification<Shipment> spec, Pageable pageable);

	ResponseEntity<ShipmentPageDTO> getShipments(ShipmentSearchDTO shipmentSearchDTO);
	
	List<ShipmentDTO> convertShipmentsToShipmentDTOs(List<Shipment> shipments, ShipmentConvertCriteriaDTO convertCriteria);

	ShipmentDTO getShipmentDTOById(Integer shipmentId);







}





