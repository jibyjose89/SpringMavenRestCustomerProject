package com.luv2code.springdemo.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

/**
 * @author Arvind Murthy
 *
 */
@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the customer Service
	@Autowired
	private CustomerService customerService;

	// add the mapping to get the customers
	@GetMapping("/customers")
	public List<Customer> getCustomer() {

		return customerService.getCustomers();

	}

	// API to retrieve a single customer
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer theCustomer = customerService.getCustomer(customerId);

		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not Found - " + customerId);
		}

		return theCustomer;
	}

	/**
	 * API to add/create new customer
	 * 
	 * @param theCustomer
	 * @return
	 */
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// If the Id is passed from Json, we explicitly
		// set it to 0 so that it will save instead of update
		// If id is 0 it will insert a new Id

		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;

	}

	/**
	 * API to update a particular Customer add mapping PUT/customers
	 * 
	 * @param theCustomer
	 * @return
	 */
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	/**
	 * API to delete the customers by Id add mapping DELETE/customers/{customerId}
	 * 
	 * @param theCustomerId
	 * @return
	 */
	@DeleteMapping("/customers/{theCustomerId}")
	public String deleteCustomer(@PathVariable int theCustomerId) {

		Customer tempCustomer = customerService.getCustomer(theCustomerId);

		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer-ID " + theCustomerId + " does not exist!");
		}

		customerService.deleteCustomer(theCustomerId);

		return "Deleted Customer id - " + theCustomerId + " : FirstName - " + tempCustomer.getFirstName() + " : LastName - "
				+ tempCustomer.getLastName() + " : Email - " + tempCustomer.getEmail();
	}

	/*
	 * Retrieve all customer URL - GET Mapping -
	 * http://localhost:8080/spring-crm-rest/api/customers
	 * 
	 * Add/Insert/Create a Customer URL - PUT Mapping -
	 * http://localhost:8080/spring-crm-rest/api/customers
	 * 
	 * Eg: { "firstName" : "Max", "lastName" :"Molly", "email" : "max@luv2code.com"
	 * }
	 * 
	 * Update a Customer -PUT Mapping URL -
	 * http://localhost:8080/spring-crm-rest/api/customers
	 * 
	 * Eg: { "id" : 14, "firstName" :"mage", "lastName" : "marvin", "email" :
	 * "mage@luv2code.com" }
	 * 
	 * Delete a Customer - Delete Mapping Url -
	 * 
	 * http://localhost:8080/spring-crm-rest/api/customers/12
	 * 
	 */

}
