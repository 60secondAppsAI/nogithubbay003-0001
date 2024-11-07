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
import com.nogithubbay003.dao.AdvertisementDAO;
import com.nogithubbay003.domain.Advertisement;
import com.nogithubbay003.dto.AdvertisementDTO;
import com.nogithubbay003.dto.AdvertisementSearchDTO;
import com.nogithubbay003.dto.AdvertisementPageDTO;
import com.nogithubbay003.dto.AdvertisementConvertCriteriaDTO;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import com.nogithubbay003.service.AdvertisementService;
import com.nogithubbay003.util.ControllerUtils;





@Service
public class AdvertisementServiceImpl extends GenericServiceImpl<Advertisement, Integer> implements AdvertisementService {

    private final static Logger logger = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

	@Autowired
	AdvertisementDAO advertisementDao;

	


	@Override
	public GenericDAO<Advertisement, Integer> getDAO() {
		return (GenericDAO<Advertisement, Integer>) advertisementDao;
	}
	
	public List<Advertisement> findAll () {
		List<Advertisement> advertisements = advertisementDao.findAll();
		
		return advertisements;	
		
	}

	public ResultDTO addAdvertisement(AdvertisementDTO advertisementDTO, RequestDTO requestDTO) {

		Advertisement advertisement = new Advertisement();

		advertisement.setAdvertisementId(advertisementDTO.getAdvertisementId());


		advertisement.setStartDate(advertisementDTO.getStartDate());


		advertisement.setEndDate(advertisementDTO.getEndDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		advertisement = advertisementDao.save(advertisement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Advertisement> getAllAdvertisements(Pageable pageable) {
		return advertisementDao.findAll(pageable);
	}

	public Page<Advertisement> getAllAdvertisements(Specification<Advertisement> spec, Pageable pageable) {
		return advertisementDao.findAll(spec, pageable);
	}

	public ResponseEntity<AdvertisementPageDTO> getAdvertisements(AdvertisementSearchDTO advertisementSearchDTO) {
	
			Integer advertisementId = advertisementSearchDTO.getAdvertisementId(); 
     			String sortBy = advertisementSearchDTO.getSortBy();
			String sortOrder = advertisementSearchDTO.getSortOrder();
			String searchQuery = advertisementSearchDTO.getSearchQuery();
			Integer page = advertisementSearchDTO.getPage();
			Integer size = advertisementSearchDTO.getSize();

	        Specification<Advertisement> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, advertisementId, "advertisementId"); 
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

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

		Page<Advertisement> advertisements = this.getAllAdvertisements(spec, pageable);
		
		//System.out.println(String.valueOf(advertisements.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(advertisements.getTotalPages()));
		
		List<Advertisement> advertisementsList = advertisements.getContent();
		
		AdvertisementConvertCriteriaDTO convertCriteria = new AdvertisementConvertCriteriaDTO();
		List<AdvertisementDTO> advertisementDTOs = this.convertAdvertisementsToAdvertisementDTOs(advertisementsList,convertCriteria);
		
		AdvertisementPageDTO advertisementPageDTO = new AdvertisementPageDTO();
		advertisementPageDTO.setAdvertisements(advertisementDTOs);
		advertisementPageDTO.setTotalElements(advertisements.getTotalElements());
		return ResponseEntity.ok(advertisementPageDTO);
	}

	public List<AdvertisementDTO> convertAdvertisementsToAdvertisementDTOs(List<Advertisement> advertisements, AdvertisementConvertCriteriaDTO convertCriteria) {
		
		List<AdvertisementDTO> advertisementDTOs = new ArrayList<AdvertisementDTO>();
		
		for (Advertisement advertisement : advertisements) {
			advertisementDTOs.add(convertAdvertisementToAdvertisementDTO(advertisement,convertCriteria));
		}
		
		return advertisementDTOs;

	}
	
	public AdvertisementDTO convertAdvertisementToAdvertisementDTO(Advertisement advertisement, AdvertisementConvertCriteriaDTO convertCriteria) {
		
		AdvertisementDTO advertisementDTO = new AdvertisementDTO();
		
		advertisementDTO.setAdvertisementId(advertisement.getAdvertisementId());

	
		advertisementDTO.setStartDate(advertisement.getStartDate());

	
		advertisementDTO.setEndDate(advertisement.getEndDate());

	

		
		return advertisementDTO;
	}

	public ResultDTO updateAdvertisement(AdvertisementDTO advertisementDTO, RequestDTO requestDTO) {
		
		Advertisement advertisement = advertisementDao.getById(advertisementDTO.getAdvertisementId());

		advertisement.setAdvertisementId(ControllerUtils.setValue(advertisement.getAdvertisementId(), advertisementDTO.getAdvertisementId()));

		advertisement.setStartDate(ControllerUtils.setValue(advertisement.getStartDate(), advertisementDTO.getStartDate()));

		advertisement.setEndDate(ControllerUtils.setValue(advertisement.getEndDate(), advertisementDTO.getEndDate()));



        advertisement = advertisementDao.save(advertisement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AdvertisementDTO getAdvertisementDTOById(Integer advertisementId) {
	
		Advertisement advertisement = advertisementDao.getById(advertisementId);
			
		
		AdvertisementConvertCriteriaDTO convertCriteria = new AdvertisementConvertCriteriaDTO();
		return(this.convertAdvertisementToAdvertisementDTO(advertisement,convertCriteria));
	}







}
