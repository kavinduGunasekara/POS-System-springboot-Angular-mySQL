package com.example.pos.controller;

import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.request.CustomerUpdateDTO;
import com.example.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "saved";
    }
    @PutMapping("/update")
    public String updatecustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
       String message=  customerService.updateCustomer(customerUpdateDTO);
        return message;
    }
    @GetMapping(
            path ="/get-by-id",
            params= "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
      CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;

    }
    @GetMapping(
            path = "/get-all-customers"
    )
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }
    @DeleteMapping(
            path = "delete-customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value ="id")int customerId){
        String deleted = customerService.deletedCustomer(customerId);
        return deleted;
    }
    @GetMapping(
            path = "/get-all-customers-by-active-state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "status")boolean activeState){
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }
}
