package com.nogithubbay003.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.nogithubbay003.domain.Advertisement;
import com.nogithubbay003.dto.AdvertisementDTO;
import com.nogithubbay003.dto.AdvertisementSearchDTO;
import com.nogithubbay003.dto.AdvertisementPageDTO;
import com.nogithubbay003.dto.AdvertisementConvertCriteriaDTO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AdvertisementService extends GenericService<Advertisement, Integer> {

	List<Advertisement> findAll();

	ResultDTO addAdvertisement(AdvertisementDTO advertisementDTO, RequestDTO requestDTO);

	ResultDTO updateAdvertisement(AdvertisementDTO advertisementDTO, RequestDTO requestDTO);

    Page<Advertisement> getAllAdvertisements(Pageable pageable);

    Page<Advertisement> getAllAdvertisements(Specification<Advertisement> spec, Pageable pageable);

	ResponseEntity<AdvertisementPageDTO> getAdvertisements(AdvertisementSearchDTO advertisementSearchDTO);
	
	List<AdvertisementDTO> convertAdvertisementsToAdvertisementDTOs(List<Advertisement> advertisements, AdvertisementConvertCriteriaDTO convertCriteria);

	AdvertisementDTO getAdvertisementDTOById(Integer advertisementId);







}





