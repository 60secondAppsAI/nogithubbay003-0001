package com.nogithubbay003.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.nogithubbay003.domain.SellerVerification;
import com.nogithubbay003.dto.SellerVerificationDTO;
import com.nogithubbay003.dto.SellerVerificationSearchDTO;
import com.nogithubbay003.dto.SellerVerificationPageDTO;
import com.nogithubbay003.dto.SellerVerificationConvertCriteriaDTO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SellerVerificationService extends GenericService<SellerVerification, Integer> {

	List<SellerVerification> findAll();

	ResultDTO addSellerVerification(SellerVerificationDTO sellerVerificationDTO, RequestDTO requestDTO);

	ResultDTO updateSellerVerification(SellerVerificationDTO sellerVerificationDTO, RequestDTO requestDTO);

    Page<SellerVerification> getAllSellerVerifications(Pageable pageable);

    Page<SellerVerification> getAllSellerVerifications(Specification<SellerVerification> spec, Pageable pageable);

	ResponseEntity<SellerVerificationPageDTO> getSellerVerifications(SellerVerificationSearchDTO sellerVerificationSearchDTO);
	
	List<SellerVerificationDTO> convertSellerVerificationsToSellerVerificationDTOs(List<SellerVerification> sellerVerifications, SellerVerificationConvertCriteriaDTO convertCriteria);

	SellerVerificationDTO getSellerVerificationDTOById(Integer sellerVerificationId);







}





