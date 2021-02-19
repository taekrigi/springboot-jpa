package com.my.jpa.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PassportModel {

	private UUID id;
	
	private String country;
	
	private String gender;
	
	private int countVisitedCountry;
	
}
