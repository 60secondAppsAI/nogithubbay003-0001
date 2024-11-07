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
import com.nogithubbay003.dao.SubscriptionDAO;
import com.nogithubbay003.domain.Subscription;
import com.nogithubbay003.dto.SubscriptionDTO;
import com.nogithubbay003.dto.SubscriptionSearchDTO;
import com.nogithubbay003.dto.SubscriptionPageDTO;
import com.nogithubbay003.dto.SubscriptionConvertCriteriaDTO;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import com.nogithubbay003.service.SubscriptionService;
import com.nogithubbay003.util.ControllerUtils;





@Service
public class SubscriptionServiceImpl extends GenericServiceImpl<Subscription, Integer> implements SubscriptionService {

    private final static Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	@Autowired
	SubscriptionDAO subscriptionDao;

	


	@Override
	public GenericDAO<Subscription, Integer> getDAO() {
		return (GenericDAO<Subscription, Integer>) subscriptionDao;
	}
	
	public List<Subscription> findAll () {
		List<Subscription> subscriptions = subscriptionDao.findAll();
		
		return subscriptions;	
		
	}

	public ResultDTO addSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO) {

		Subscription subscription = new Subscription();

		subscription.setSubscriptionId(subscriptionDTO.getSubscriptionId());


		subscription.setPlan(subscriptionDTO.getPlan());


		subscription.setStartDate(subscriptionDTO.getStartDate());


		subscription.setRenewalDate(subscriptionDTO.getRenewalDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		subscription = subscriptionDao.save(subscription);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Subscription> getAllSubscriptions(Pageable pageable) {
		return subscriptionDao.findAll(pageable);
	}

	public Page<Subscription> getAllSubscriptions(Specification<Subscription> spec, Pageable pageable) {
		return subscriptionDao.findAll(spec, pageable);
	}

	public ResponseEntity<SubscriptionPageDTO> getSubscriptions(SubscriptionSearchDTO subscriptionSearchDTO) {
	
			Integer subscriptionId = subscriptionSearchDTO.getSubscriptionId(); 
 			String plan = subscriptionSearchDTO.getPlan(); 
     			String sortBy = subscriptionSearchDTO.getSortBy();
			String sortOrder = subscriptionSearchDTO.getSortOrder();
			String searchQuery = subscriptionSearchDTO.getSearchQuery();
			Integer page = subscriptionSearchDTO.getPage();
			Integer size = subscriptionSearchDTO.getSize();

	        Specification<Subscription> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, subscriptionId, "subscriptionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, plan, "plan"); 
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("plan")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Subscription> subscriptions = this.getAllSubscriptions(spec, pageable);
		
		//System.out.println(String.valueOf(subscriptions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(subscriptions.getTotalPages()));
		
		List<Subscription> subscriptionsList = subscriptions.getContent();
		
		SubscriptionConvertCriteriaDTO convertCriteria = new SubscriptionConvertCriteriaDTO();
		List<SubscriptionDTO> subscriptionDTOs = this.convertSubscriptionsToSubscriptionDTOs(subscriptionsList,convertCriteria);
		
		SubscriptionPageDTO subscriptionPageDTO = new SubscriptionPageDTO();
		subscriptionPageDTO.setSubscriptions(subscriptionDTOs);
		subscriptionPageDTO.setTotalElements(subscriptions.getTotalElements());
		return ResponseEntity.ok(subscriptionPageDTO);
	}

	public List<SubscriptionDTO> convertSubscriptionsToSubscriptionDTOs(List<Subscription> subscriptions, SubscriptionConvertCriteriaDTO convertCriteria) {
		
		List<SubscriptionDTO> subscriptionDTOs = new ArrayList<SubscriptionDTO>();
		
		for (Subscription subscription : subscriptions) {
			subscriptionDTOs.add(convertSubscriptionToSubscriptionDTO(subscription,convertCriteria));
		}
		
		return subscriptionDTOs;

	}
	
	public SubscriptionDTO convertSubscriptionToSubscriptionDTO(Subscription subscription, SubscriptionConvertCriteriaDTO convertCriteria) {
		
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		
		subscriptionDTO.setSubscriptionId(subscription.getSubscriptionId());

	
		subscriptionDTO.setPlan(subscription.getPlan());

	
		subscriptionDTO.setStartDate(subscription.getStartDate());

	
		subscriptionDTO.setRenewalDate(subscription.getRenewalDate());

	

		
		return subscriptionDTO;
	}

	public ResultDTO updateSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO) {
		
		Subscription subscription = subscriptionDao.getById(subscriptionDTO.getSubscriptionId());

		subscription.setSubscriptionId(ControllerUtils.setValue(subscription.getSubscriptionId(), subscriptionDTO.getSubscriptionId()));

		subscription.setPlan(ControllerUtils.setValue(subscription.getPlan(), subscriptionDTO.getPlan()));

		subscription.setStartDate(ControllerUtils.setValue(subscription.getStartDate(), subscriptionDTO.getStartDate()));

		subscription.setRenewalDate(ControllerUtils.setValue(subscription.getRenewalDate(), subscriptionDTO.getRenewalDate()));



        subscription = subscriptionDao.save(subscription);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SubscriptionDTO getSubscriptionDTOById(Integer subscriptionId) {
	
		Subscription subscription = subscriptionDao.getById(subscriptionId);
			
		
		SubscriptionConvertCriteriaDTO convertCriteria = new SubscriptionConvertCriteriaDTO();
		return(this.convertSubscriptionToSubscriptionDTO(subscription,convertCriteria));
	}







}
