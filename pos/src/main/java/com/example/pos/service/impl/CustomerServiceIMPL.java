package com.example.pos.service.impl;

import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.request.CustomerUpdateDTO;
import com.example.pos.entity.Customer;
import com.example.pos.exception.NotFoundException;
import com.example.pos.repo.CustomerRepo;
import com.example.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    public CustomerRepo customerRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomer_salary(),
                customerDTO.getContactNumber(),
                customerDTO.getNic(),
                customerDTO.isActive()
        );
        // Customer customer = new Customer();
        customerRepo.save(customer);
        return customerDTO.getCustomerName();
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if (customerRepo.existsById(customerUpdateDTO.getCustomerId())) {
            Customer customer = customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            customer.setCustomerName(customerUpdateDTO.getCustomerName());
            customer.setCustomerAddress(customerUpdateDTO.getCustomerAddress());
            customer.setCustomer_salary(customerUpdateDTO.getCustomer_salary());
            customerRepo.save(customer);
            return customerUpdateDTO.getCustomerName() + "Update Successful";


        } else {
            throw new RuntimeException("no data found for that id");
        }
    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if (customerRepo.existsById(customerId)) {
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomer_salary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()

            );

            return customerDTO;
        } else {
            throw new RuntimeException("No customer");

        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getAllCustomers = customerRepo.findAll();
        if (getAllCustomers.size()>0) {

            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for (Customer customer : getAllCustomers) {
                CustomerDTO customerDTO = new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getCustomerName(),
                        customer.getCustomerAddress(),
                        customer.getCustomer_salary(),
                        customer.getContactNumber(),
                        customer.getNic(),
                        customer.isActive()

                );

                customerDTOList.add(customerDTO);


            }
            return customerDTOList;
        }else {
            throw new NotFoundException("No customer found");
        }
    }

    @Override
    public String deletedCustomer(int customerId) {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);
            return "deleted successfully" + customerId;

        }else{
            throw new RuntimeException("No customer found In That Id");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByActiveState(boolean activeState) {
        List<Customer> getAllCustomers = customerRepo.findAllByActiveEquals(activeState);
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : getAllCustomers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getCustomerId(),
                    customer.getCustomerName(),
                    customer.getCustomerAddress(),
                    customer.getCustomer_salary(),
                    customer.getContactNumber(),
                    customer.getNic(),
                    customer.isActive()

            );

            customerDTOList.add(customerDTO);


        }
        return customerDTOList;
    }

}

