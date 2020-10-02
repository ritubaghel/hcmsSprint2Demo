package com.capgemini.healthcaremanagementsystem.service;

import java.util.List;

import javax.validation.Valid;

import com.capgemini.healthcaremanagementsystem.entity.Test;
import com.capgemini.healthcaremanagementsystem.entity.Admin;
import com.capgemini.healthcaremanagementsystem.entity.Center;

public interface AdminService {

	List<Center> getAllCenter();

	Center getCenterById(Long id);

	Center createCenter(@Valid Center center);

	String deleteCenter(Long id);

	Test addTest(Long centerId, @Valid Test test);

	String deleteTest(Long centerId, Long testId);

	Admin adminLogin(String adminName, String adminPassword);

	
	


}
