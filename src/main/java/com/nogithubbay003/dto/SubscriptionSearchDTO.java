package com.nogithubbay003.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscriptionSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer subscriptionId;
	
	private String plan;
	
	private Date startDate;
	
	private Date renewalDate;
	
}
