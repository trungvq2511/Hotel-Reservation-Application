package org.example.service;

import org.example.HotelApplication;
import org.example.model.Customer;

import java.util.Collection;

import static org.example.HotelApplication.customerList;

public class CustomerService {
    private static CustomerService singleInstance = null;

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if (singleInstance == null) {
            CustomerService singleInstance = new CustomerService();
            CustomerService.singleInstance = singleInstance;
        }
        return singleInstance;
    }

    public void addCustomer(String firstName, String lastName, String email) {
        HotelApplication.customerList.add(new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equalsIgnoreCase(customerEmail)) {
                return customer;
            }
        }
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return HotelApplication.customerList;
    }
}
