package com.capgemini.healthcaremanagementsystem.service;

import com.capgemini.healthcaremanagementsystem.entity.Customer;
import com.capgemini.healthcaremanagementsystem.entity.Test;

public interface CustomerService {

	Test getTestById(Long id);

	Customer registerCustomer(Customer customer);

	Customer customerLogin(String customerName, String customerPassword);

	

}
