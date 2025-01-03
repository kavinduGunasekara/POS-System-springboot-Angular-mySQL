package com.example.pos.dto.request;

public class CustomerUpdateDTO {


    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customer_salary;


    public CustomerUpdateDTO() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getCustomer_salary() {
        return customer_salary;
    }

    @Override
    public String toString() {
        return "CustomerUpdateDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customer_salary=" + customer_salary +
                '}';
    }

    public void setCustomer_salary(double customer_salary) {
        this.customer_salary = customer_salary;
    }

    public CustomerUpdateDTO(int customerId, String customerName, String customerAddress, double customer_salary) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customer_salary = customer_salary;
    }
}
