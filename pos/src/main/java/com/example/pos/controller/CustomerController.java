package com.example.pos.controller;

import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.request.CustomerUpdateDTO;
import com.example.pos.service.CustomerService;
import com.example.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /*
    @PostMapping("/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "saved";
    } */

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        String message = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK
        );
    }


    /*
    @PutMapping("/update")
    public String updatecustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
       String message=  customerService.updatecustomer(customerUpdateDTO);
        return message;
    }
    */


    @PutMapping("/update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        String message = customerService.updateCustomer(customerUpdateDTO);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", message),
                HttpStatus.OK
        );
    }


    /* @GetMapping(
             path ="/get-by-id",
             params= "id"
     )
     public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
       CustomerDTO customerDTO = customerService.getCustomerById(customerId);
         return customerDTO;

     }
     */
    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") int customerId) {
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(
                new StandardResponse(200, "Success", customerDTO),
                HttpStatus.OK
        );
    }



    /*
    @GetMapping(
            path = "/get-all-customers"
    )
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }
    */

    @GetMapping(
            path = "/get-all-customers"
    )
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", allCustomers),
                HttpStatus.OK
        );
    }


    @DeleteMapping(
            path = "delete-customer/{id}"
    )
    public String deleteCustomer(@PathVariable(value = "id") int customerId) {
        String deleted = customerService.deletedCustomer(customerId);
        return deleted;
    }

    @GetMapping(
            path = "/get-all-customers-by-active-state/{status}"
    )
    public List<CustomerDTO> getAllCustomersByActiveState(@PathVariable(value = "status") boolean activeState) {
        List<CustomerDTO> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return allCustomers;
    }
}
