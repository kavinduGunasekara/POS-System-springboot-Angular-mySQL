package com.example.pos.controller;


import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.request.ItemSaveRequestDTO;
import com.example.pos.dto.response.ItemGetResponseDTO;
import com.example.pos.service.ItemService;
import com.example.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin


public class ItemController {
    @Autowired
    private ItemService itemService;

 /*   @PostMapping(
            path ={"/save"}
    )

    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message=  itemService.saveItem(itemSaveRequestDTO);
        return "saved";
    } */





    @PostMapping(
            path ={"/save"}
    )

    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
       String message=  itemService.saveItem(itemSaveRequestDTO);
    /*  ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
              new StandardResponse(201, "Success" , message), HttpStatus.CREATED

      );*/

      return new ResponseEntity<StandardResponse>(
              new StandardResponse(201, "Success" , message),
              HttpStatus.CREATED

      );
    }

    @GetMapping(
            path="/get-by-name-with-mapstruct",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(@RequestParam(value ="name")String itemName){
        List<ItemGetResponseDTO>itemDTOS = itemService.getItemByNameAndStatusByMapstruct(itemName);
        return itemDTOS;

    }

    @GetMapping(
            path="/get-by-name-with-status",
            params = "activeStatus"
    )
    public ResponseEntity<StandardResponse>getItemsByActiveStatus(@RequestParam(value ="activeStatus")boolean activeStatus){
        List<ItemGetResponseDTO>itemDTOS = itemService.getItemsByActiveStatus(activeStatus);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemDTOS),
                HttpStatus.OK
        );

    }

}
