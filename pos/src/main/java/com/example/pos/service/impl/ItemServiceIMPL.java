package com.example.pos.service.impl;

import com.example.pos.dto.request.ItemSaveRequestDTO;
import com.example.pos.entity.Customer;
import com.example.pos.entity.Item;
import com.example.pos.entity.enums.MeasuringUnitType;
import com.example.pos.repo.ItemRepo;
import com.example.pos.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
      /* Item item = new Item(
                1,
              itemSaveRequestDTO.getItemName(),
              itemSaveRequestDTO.getMeasuringUnitType(),
                itemSaveRequestDTO.getBalanceQty(),
               itemSaveRequestDTO.getSupplierPrice(),
                itemSaveRequestDTO.getSellingPrice(),
              true
      );
         Customer customer = new Customer();
       itemRepo.save(item);
         return item.getItemName();  */
        Item item =modelMapper.map(itemSaveRequestDTO,Item.class);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemId() + "saved successfully";
        }else{
            throw new DuplicateKeyException("A;ready added");
        }

    }

}
