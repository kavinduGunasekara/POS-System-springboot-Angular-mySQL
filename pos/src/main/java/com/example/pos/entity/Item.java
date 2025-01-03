package com.example.pos.entity;

import com.example.pos.entity.enums.MeasuringUnitType;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="item")
public class Item {
    @Id
    @Column(name= "item_id",length=45)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int itemId;

    @Column(name="item_name",length =100,nullable=false)
    private  String itemName;


    @Enumerated(EnumType.STRING)
    @Column(name="measure_type",length =100,nullable=false)
    private MeasuringUnitType measuringUnitType;

    @Column(name= "balance_qty", length= 100, nullable= false)
    private double balanceQty;

    @Column(name= "supplier_price", length= 100, nullable= false)
    private double supplierPrice;

    @Column(name= "sellingPrice", length= 100, nullable= false)
    private double sellingPrice;

    @Column(name = "active_state", columnDefinition = "TINYINT default 1")
    private boolean active;


}
