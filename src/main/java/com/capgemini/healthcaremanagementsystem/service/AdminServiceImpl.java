package com.capgemini.healthcaremanagementsystem.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaremanagementsystem.dao.AdminDao;
import com.capgemini.healthcaremanagementsystem.dao.CenterDao;
import com.capgemini.healthcaremanagementsystem.dao.TestDao;
import com.capgemini.healthcaremanagementsystem.entity.Admin;
import com.capgemini.healthcaremanagementsystem.entity.Center;
import com.capgemini.healthcaremanagementsystem.entity.Test;
import com.capgemini.healthcaremanagementsystem.exception.AdminNotFoundException;
import com.capgemini.healthcaremanagementsystem.exception.CenterDoesNotExistsException;
import com.capgemini.healthcaremanagementsystem.exception.CenterNotFoundException;
import com.capgemini.healthcaremanagementsystem.exception.TestIdDoesNotExistException;

@Service

public class AdminServiceImpl implements AdminService {

	@Autowired
	private CenterDao centerDao;

	@Autowired
	private TestDao testDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin adminLogin(String adminName, String adminPassword) throws AdminNotFoundException{
		Admin admin = adminDao.findAdminByAdminName(adminName);
		if ((admin!=null && admin.getAdminPassword().equals(adminPassword) && admin.getAdminName().equals(adminName)))
			return admin;
		throw new AdminNotFoundException(
				"Admin with AdminName [" + adminName + "] and password [" + adminPassword + "] not found");
	}
	

	// get all center
	@Override
	public List<Center> getAllCenter() {
		return centerDao.findAll();
	}

	// get center by id
	@Override
	public Center getCenterById(Long id) {
		Optional<Center> centerList = centerDao.findById(id);
		if (centerList.isPresent()) {
			return centerList.get();
		} else {
			throw new CenterDoesNotExistsException("Center with "+ id+" not found!!");
		}
	}

	// create center
	@Override
	public Center createCenter(@Valid Center center) {
		return centerDao.save(center);
	}

	// delete student
	@Override
	public String deleteCenter(Long id) {
		return centerDao.findById(id).map(center -> {
			centerDao.delete(center);
			return "Delete Successfully!";
		}).orElseThrow(() -> new CenterDoesNotExistsException("Center with "+ id+" not found!!"));
	}


	// Add tests to center
	@Override
	public Test addTest(Long centerId, @Valid Test test) {
		return centerDao.findById(centerId).map(center -> {
			test.setCenter(center);
			return testDao.save(test);
		}).orElseThrow(() -> new CenterNotFoundException("Center not found!"));

	}

	// Delete test from center
	@Override
	public String deleteTest(Long centerId, Long testId) {
		if (!centerDao.existsById(centerId)) {
			throw new CenterDoesNotExistsException("Center with "+ centerId+" not found!!");
		}

		return testDao.findById(testId).map(test -> {
			testDao.delete(test);
			return "Deleted Successfully!";
		}).orElseThrow(() -> new TestIdDoesNotExistException("Test with "+ testId+" not found!!"));
	}



	
}
