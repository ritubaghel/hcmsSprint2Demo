package com.capgemini.healthcaremanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.healthcaremanagementsystem.entity.Customer;
import com.capgemini.healthcaremanagementsystem.entity.Test;
import com.capgemini.healthcaremanagementsystem.service.AdminService;
import com.capgemini.healthcaremanagementsystem.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(value = "http://localhost:4200")
public class CustomerController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customer")
	public Customer registerCustomer(@RequestBody Customer customer){
		return customerService.registerCustomer(customer);
	}
	
	@PostMapping("/login")
	public Customer customerLogin(@RequestBody Customer customer) {
		String customerName= customer.getCustomerName();
		String customerPassword=customer.getCustomerPassword();
		return customerService.customerLogin(customerName,customerPassword);
	}
	
	//get test by id
    @GetMapping("/test/{id}")
    public Test getTestById(@PathVariable Long id) {
    	return customerService.getTestById(id);
    }
    	
	
	
		
}
