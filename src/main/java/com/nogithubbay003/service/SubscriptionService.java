package com.nogithubbay003.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.nogithubbay003.domain.Subscription;
import com.nogithubbay003.dto.SubscriptionDTO;
import com.nogithubbay003.dto.SubscriptionSearchDTO;
import com.nogithubbay003.dto.SubscriptionPageDTO;
import com.nogithubbay003.dto.SubscriptionConvertCriteriaDTO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SubscriptionService extends GenericService<Subscription, Integer> {

	List<Subscription> findAll();

	ResultDTO addSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO);

	ResultDTO updateSubscription(SubscriptionDTO subscriptionDTO, RequestDTO requestDTO);

    Page<Subscription> getAllSubscriptions(Pageable pageable);

    Page<Subscription> getAllSubscriptions(Specification<Subscription> spec, Pageable pageable);

	ResponseEntity<SubscriptionPageDTO> getSubscriptions(SubscriptionSearchDTO subscriptionSearchDTO);
	
	List<SubscriptionDTO> convertSubscriptionsToSubscriptionDTOs(List<Subscription> subscriptions, SubscriptionConvertCriteriaDTO convertCriteria);

	SubscriptionDTO getSubscriptionDTOById(Integer subscriptionId);







}





