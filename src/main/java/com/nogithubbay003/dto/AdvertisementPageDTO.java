package com.nogithubbay003.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdvertisementPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<AdvertisementDTO> advertisements;
}





