package com.my.jpa.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<E, D, P> {
	
	E toEntity(P params);
	
	D toModel(E entity);
	
	default List<D> toListModel(List<E> entities) {
		return entities.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
}
