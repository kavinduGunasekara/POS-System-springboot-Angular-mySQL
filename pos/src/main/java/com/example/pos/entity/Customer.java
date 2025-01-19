package com.example.pos.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;

import javax.persistence.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
//import org.hibernate.annotations.JdbcTypeCode;

import java.util.ArrayList;
import java.util.Set;


@Entity
@Table(name = "customer")
@TypeDefs({
        @TypeDef(name ="json" , typeClass = JsonType.class)
})
public class Customer {

    @Id
    @Column(name = "customer_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "customer_address", length = 255)
    private String customerAddress;

    @Column(name = "customer_salary")
    private double customer_salary;


    @Type(type = "json")
    @Column(name = "contact_number", columnDefinition = "json")
    private ArrayList contactNumber;

    @Column(name = "nic")
    private String nic;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean active;


    @OneToMany(mappedBy="customer")
    private Set<Order> orders;




    public Customer() {    // no args constuctor

    }

    public Customer(int customerId, String customerName, String customerAddress, double customer_salary, ArrayList contactNumber, String nic, boolean active) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customer_salary = customer_salary;
        this.contactNumber = contactNumber;
        this.nic = nic;
        this.active = active;
    }

    public Customer(int customerId, String customerName) {

    this.customerId =customerId;
    this.customerName=customerName;
}

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public double getCustomer_salary() {
        return customer_salary;
    }

    public ArrayList getContactNumber() {
        return contactNumber;
    }

    public String getNic() {
        return nic;
    }

    public boolean isActive() {
        return active;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomer_salary(double customer_salary) {
        this.customer_salary = customer_salary;
    }

    public void setContactNumber(ArrayList contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setActive(boolean activeState) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customer_salary=" + customer_salary +
                ", contactNumber=" + contactNumber +
                ", nic='" + nic + '\'' +
                ", active=" + active +
                '}';
    }
}
