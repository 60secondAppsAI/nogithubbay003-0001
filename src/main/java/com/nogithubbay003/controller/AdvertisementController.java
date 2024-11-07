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

import com.nogithubbay003.domain.Advertisement;
import com.nogithubbay003.dto.AdvertisementDTO;
import com.nogithubbay003.dto.AdvertisementSearchDTO;
import com.nogithubbay003.dto.AdvertisementPageDTO;
import com.nogithubbay003.service.AdvertisementService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/advertisement")
@RestController
public class AdvertisementController {

	private final static Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

	@Autowired
	AdvertisementService advertisementService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Advertisement> getAll() {

		List<Advertisement> advertisements = advertisementService.findAll();
		
		return advertisements;	
	}

	@GetMapping(value = "/{advertisementId}")
	@ResponseBody
	public AdvertisementDTO getAdvertisement(@PathVariable Integer advertisementId) {
		
		return (advertisementService.getAdvertisementDTOById(advertisementId));
	}

 	@RequestMapping(value = "/addAdvertisement", method = RequestMethod.POST)
	public ResponseEntity<?> addAdvertisement(@RequestBody AdvertisementDTO advertisementDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = advertisementService.addAdvertisement(advertisementDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/advertisements")
	public ResponseEntity<AdvertisementPageDTO> getAdvertisements(AdvertisementSearchDTO advertisementSearchDTO) {
 
		return advertisementService.getAdvertisements(advertisementSearchDTO);
	}	

	@RequestMapping(value = "/updateAdvertisement", method = RequestMethod.POST)
	public ResponseEntity<?> updateAdvertisement(@RequestBody AdvertisementDTO advertisementDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = advertisementService.updateAdvertisement(advertisementDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
