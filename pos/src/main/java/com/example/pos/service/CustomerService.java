package com.example.pos.service;

import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    String deletedCustomer(int customerId);

    List<CustomerDTO> getAllCustomersByActiveState(boolean activeState);
}
