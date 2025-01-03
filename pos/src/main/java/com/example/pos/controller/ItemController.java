package com.example.pos.controller;


import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.request.ItemSaveRequestDTO;
import com.example.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin


public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(
            path ={"/save"}
    )

    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
       String message=  itemService.saveItem(itemSaveRequestDTO);
        return "saved";
    }

}
