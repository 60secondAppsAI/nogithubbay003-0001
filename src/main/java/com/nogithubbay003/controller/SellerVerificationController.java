package com.nogithubbay003.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.nogithubbay003.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.nogithubbay003.domain.SellerVerification;
import com.nogithubbay003.dto.SellerVerificationDTO;
import com.nogithubbay003.dto.SellerVerificationSearchDTO;
import com.nogithubbay003.dto.SellerVerificationPageDTO;
import com.nogithubbay003.service.SellerVerificationService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/sellerVerification")
@RestController
public class SellerVerificationController {

	private final static Logger logger = LoggerFactory.getLogger(SellerVerificationController.class);

	@Autowired
	SellerVerificationService sellerVerificationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SellerVerification> getAll() {

		List<SellerVerification> sellerVerifications = sellerVerificationService.findAll();
		
		return sellerVerifications;	
	}

	@GetMapping(value = "/{sellerVerificationId}")
	@ResponseBody
	public SellerVerificationDTO getSellerVerification(@PathVariable Integer sellerVerificationId) {
		
		return (sellerVerificationService.getSellerVerificationDTOById(sellerVerificationId));
	}

 	@RequestMapping(value = "/addSellerVerification", method = RequestMethod.POST)
	public ResponseEntity<?> addSellerVerification(@RequestBody SellerVerificationDTO sellerVerificationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sellerVerificationService.addSellerVerification(sellerVerificationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/sellerVerifications")
	public ResponseEntity<SellerVerificationPageDTO> getSellerVerifications(SellerVerificationSearchDTO sellerVerificationSearchDTO) {
 
		return sellerVerificationService.getSellerVerifications(sellerVerificationSearchDTO);
	}	

	@RequestMapping(value = "/updateSellerVerification", method = RequestMethod.POST)
	public ResponseEntity<?> updateSellerVerification(@RequestBody SellerVerificationDTO sellerVerificationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = sellerVerificationService.updateSellerVerification(sellerVerificationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
