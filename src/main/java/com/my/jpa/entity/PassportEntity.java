package com.my.jpa.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "PASSPORT")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassportEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private UUID id;
	
	@Column
	private String country;
	
	@Column
	private String gender;
	
	@Column
	private int countVisitedCountry;
	
	@OneToOne(mappedBy = "passport")
	private UserEntity user;
	
	
	
	
	
	
	
}
