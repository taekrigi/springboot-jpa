package com.my.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<E, M, P> {
	
	E toEntity(P params);
	
	M toModel(E entity);
	
	default List<M> toListModel(List<E> entities) {
		return entities.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
}
