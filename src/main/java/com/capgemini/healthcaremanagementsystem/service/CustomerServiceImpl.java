package com.capgemini.healthcaremanagementsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.healthcaremanagementsystem.dao.CenterDao;
import com.capgemini.healthcaremanagementsystem.dao.CustomerDao;
import com.capgemini.healthcaremanagementsystem.dao.TestDao;
import com.capgemini.healthcaremanagementsystem.entity.Customer;
import com.capgemini.healthcaremanagementsystem.entity.Test;
import com.capgemini.healthcaremanagementsystem.exception.AdminNotFoundException;
import com.capgemini.healthcaremanagementsystem.exception.ContactNumberAlreadyExistException;
import com.capgemini.healthcaremanagementsystem.exception.CustomerNameAlreadyExistException;
import com.capgemini.healthcaremanagementsystem.exception.EmailAlreadyExistException;
import com.capgemini.healthcaremanagementsystem.exception.TestIdDoesNotExistException;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CenterDao centerDao;
	
	@Autowired
	private TestDao testDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public Customer registerCustomer(Customer customer)  {

		if (customerDao.findCustomerByCustomerName(customer.getCustomerName()) != null)
			throw new CustomerNameAlreadyExistException(
					"customer with Name " + customer.getCustomerName() + " is Already Exist");

		if (customerDao.findCustomerByCustomerPhoneNumber(customer.getCustomerPhoneNumber()) != null)
			throw new ContactNumberAlreadyExistException(
					"customer with ContactNumber " + customer.getCustomerPhoneNumber() + " is Already Exist");

		if (customerDao.findCustomerByCustomerEmail(customer.getCustomerEmail()) != null)
			throw new EmailAlreadyExistException(
					"customer with Email " + customer.getCustomerEmail() + " is Already Exist");


		customer = customerDao.save(customer);
		return customer;
	}
	
	@Override
	public Customer customerLogin(String customerName, String customerPassword){
		Customer customer = customerDao.findCustomerByCustomerName(customerName);
		if ((customer!=null && customer.getCustomerPassword().equals(customerPassword) && customer.getCustomerName().equals(customerName)))
			return customer;
		throw new AdminNotFoundException(
				"Admin with AdminName [" + customerName + "] and password [" + customerPassword + "] not found");
	}
	
	
	
	

	@Override
	public Test getTestById(Long id) {

			Optional<Test> testList = testDao.findById(id);
			if (testList.isPresent()) {
				return testList.get();
			} else {
				throw new TestIdDoesNotExistException("Test with "+ id+" not found!!");
			}
		}

}
