package com.my.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.jpa.entity.BoardEntity;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
 
