package com.capgemini.healthcaremanagementsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.healthcaremanagementsystem.entity.Test;

@Repository
public interface TestDao extends JpaRepository<Test, Long> {

	//List<Test> findByCenterId(Long centerId);
	

	
}
