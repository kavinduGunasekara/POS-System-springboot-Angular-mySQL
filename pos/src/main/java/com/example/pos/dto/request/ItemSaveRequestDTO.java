package com.example.pos.dto.request;


import com.example.pos.entity.enums.MeasuringUnitType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemSaveRequestDTO {

    private  String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQty;
    private double supplierPrice;
    private double sellingPrice;

}
