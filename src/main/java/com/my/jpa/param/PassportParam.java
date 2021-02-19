package com.my.jpa.param;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class PassportParam {
	
	@NotBlank
	private String country;
	
	@NotBlank
	private String gender;
	
	private int countVisitedCountry;
}
