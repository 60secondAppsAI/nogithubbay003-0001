package com.nogithubbay003.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.nogithubbay003.dao.GenericDAO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.service.impl.GenericServiceImpl;
import com.nogithubbay003.dao.SellerVerificationDAO;
import com.nogithubbay003.domain.SellerVerification;
import com.nogithubbay003.dto.SellerVerificationDTO;
import com.nogithubbay003.dto.SellerVerificationSearchDTO;
import com.nogithubbay003.dto.SellerVerificationPageDTO;
import com.nogithubbay003.dto.SellerVerificationConvertCriteriaDTO;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import com.nogithubbay003.service.SellerVerificationService;
import com.nogithubbay003.util.ControllerUtils;





@Service
public class SellerVerificationServiceImpl extends GenericServiceImpl<SellerVerification, Integer> implements SellerVerificationService {

    private final static Logger logger = LoggerFactory.getLogger(SellerVerificationServiceImpl.class);

	@Autowired
	SellerVerificationDAO sellerVerificationDao;

	


	@Override
	public GenericDAO<SellerVerification, Integer> getDAO() {
		return (GenericDAO<SellerVerification, Integer>) sellerVerificationDao;
	}
	
	public List<SellerVerification> findAll () {
		List<SellerVerification> sellerVerifications = sellerVerificationDao.findAll();
		
		return sellerVerifications;	
		
	}

	public ResultDTO addSellerVerification(SellerVerificationDTO sellerVerificationDTO, RequestDTO requestDTO) {

		SellerVerification sellerVerification = new SellerVerification();

		sellerVerification.setSellerVerificationId(sellerVerificationDTO.getSellerVerificationId());


		sellerVerification.setStatus(sellerVerificationDTO.getStatus());


		sellerVerification.setVerificationDate(sellerVerificationDTO.getVerificationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		sellerVerification = sellerVerificationDao.save(sellerVerification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SellerVerification> getAllSellerVerifications(Pageable pageable) {
		return sellerVerificationDao.findAll(pageable);
	}

	public Page<SellerVerification> getAllSellerVerifications(Specification<SellerVerification> spec, Pageable pageable) {
		return sellerVerificationDao.findAll(spec, pageable);
	}

	public ResponseEntity<SellerVerificationPageDTO> getSellerVerifications(SellerVerificationSearchDTO sellerVerificationSearchDTO) {
	
			Integer sellerVerificationId = sellerVerificationSearchDTO.getSellerVerificationId(); 
 			String status = sellerVerificationSearchDTO.getStatus(); 
   			String sortBy = sellerVerificationSearchDTO.getSortBy();
			String sortOrder = sellerVerificationSearchDTO.getSortOrder();
			String searchQuery = sellerVerificationSearchDTO.getSearchQuery();
			Integer page = sellerVerificationSearchDTO.getPage();
			Integer size = sellerVerificationSearchDTO.getSize();

	        Specification<SellerVerification> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, sellerVerificationId, "sellerVerificationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<SellerVerification> sellerVerifications = this.getAllSellerVerifications(spec, pageable);
		
		//System.out.println(String.valueOf(sellerVerifications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(sellerVerifications.getTotalPages()));
		
		List<SellerVerification> sellerVerificationsList = sellerVerifications.getContent();
		
		SellerVerificationConvertCriteriaDTO convertCriteria = new SellerVerificationConvertCriteriaDTO();
		List<SellerVerificationDTO> sellerVerificationDTOs = this.convertSellerVerificationsToSellerVerificationDTOs(sellerVerificationsList,convertCriteria);
		
		SellerVerificationPageDTO sellerVerificationPageDTO = new SellerVerificationPageDTO();
		sellerVerificationPageDTO.setSellerVerifications(sellerVerificationDTOs);
		sellerVerificationPageDTO.setTotalElements(sellerVerifications.getTotalElements());
		return ResponseEntity.ok(sellerVerificationPageDTO);
	}

	public List<SellerVerificationDTO> convertSellerVerificationsToSellerVerificationDTOs(List<SellerVerification> sellerVerifications, SellerVerificationConvertCriteriaDTO convertCriteria) {
		
		List<SellerVerificationDTO> sellerVerificationDTOs = new ArrayList<SellerVerificationDTO>();
		
		for (SellerVerification sellerVerification : sellerVerifications) {
			sellerVerificationDTOs.add(convertSellerVerificationToSellerVerificationDTO(sellerVerification,convertCriteria));
		}
		
		return sellerVerificationDTOs;

	}
	
	public SellerVerificationDTO convertSellerVerificationToSellerVerificationDTO(SellerVerification sellerVerification, SellerVerificationConvertCriteriaDTO convertCriteria) {
		
		SellerVerificationDTO sellerVerificationDTO = new SellerVerificationDTO();
		
		sellerVerificationDTO.setSellerVerificationId(sellerVerification.getSellerVerificationId());

	
		sellerVerificationDTO.setStatus(sellerVerification.getStatus());

	
		sellerVerificationDTO.setVerificationDate(sellerVerification.getVerificationDate());

	

		
		return sellerVerificationDTO;
	}

	public ResultDTO updateSellerVerification(SellerVerificationDTO sellerVerificationDTO, RequestDTO requestDTO) {
		
		SellerVerification sellerVerification = sellerVerificationDao.getById(sellerVerificationDTO.getSellerVerificationId());

		sellerVerification.setSellerVerificationId(ControllerUtils.setValue(sellerVerification.getSellerVerificationId(), sellerVerificationDTO.getSellerVerificationId()));

		sellerVerification.setStatus(ControllerUtils.setValue(sellerVerification.getStatus(), sellerVerificationDTO.getStatus()));

		sellerVerification.setVerificationDate(ControllerUtils.setValue(sellerVerification.getVerificationDate(), sellerVerificationDTO.getVerificationDate()));



        sellerVerification = sellerVerificationDao.save(sellerVerification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SellerVerificationDTO getSellerVerificationDTOById(Integer sellerVerificationId) {
	
		SellerVerification sellerVerification = sellerVerificationDao.getById(sellerVerificationId);
			
		
		SellerVerificationConvertCriteriaDTO convertCriteria = new SellerVerificationConvertCriteriaDTO();
		return(this.convertSellerVerificationToSellerVerificationDTO(sellerVerification,convertCriteria));
	}







}
