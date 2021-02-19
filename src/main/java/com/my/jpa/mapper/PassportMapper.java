package com.my.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.my.jpa.entity.PassportEntity;
import com.my.jpa.model.PassportModel;
import com.my.jpa.param.PassportParam;

@Component
public class PassportMapper {
	
	public PassportModel toModel(PassportEntity passportEntity) {
		return PassportModel.builder()
				.id(passportEntity.getId())
				.country(passportEntity.getCountry())
				.gender(passportEntity.getGender())
				.countVisitedCountry(passportEntity.getCountVisitedCountry())
				.build();
	}
	
	public PassportEntity toEntity(PassportParam passportParam) {
		return PassportEntity.builder()
				.country(passportParam.getCountry())
				.gender(passportParam.getGender())
				.countVisitedCountry(passportParam.getCountVisitedCountry())
				.build();
	}
	
	public List<PassportModel> toListModel(List<PassportEntity> passportEntities) {
		return passportEntities.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

}
