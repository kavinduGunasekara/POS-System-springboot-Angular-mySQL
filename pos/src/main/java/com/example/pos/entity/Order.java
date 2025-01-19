package com.example.pos.entity;


import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@TypeDefs({
        @TypeDef(name="json",typeClass = JsonType.class)
})
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Order {
    @Id
    @Column(name="order_id", length=45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderid;

    @ManyToOne
    @JoinColumn(name="cartomer_id", nullable=false)
    private Customer customer;

    @Column(name = "order_date" , columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total" , nullable = false)
    private Double total;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;







}
