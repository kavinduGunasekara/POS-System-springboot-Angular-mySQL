package com.example.pos.controller;


import com.example.pos.dto.CustomerDTO;
import com.example.pos.dto.paginated.PaginatedResponseItemDTO;
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
            params = {"activeStatus", "page", "size"}
    )
    public ResponseEntity<StandardResponse>getItemsByActiveStatus(
            @RequestParam(value ="activeStatus")boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")int size

            ){
      //  List<ItemGetResponseDTO>itemDTOS = itemService.getItemsByActiveStatus(activeStatus,page,size);
        PaginatedResponseItemDTO paginatedResponseItemDTO =itemService.getItemsByActiveStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );

    }


    @GetMapping(
            path="/get-all-items-paginated",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse>getAllItemsPaginated(

            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")int size

    ){
        //  List<ItemGetResponseDTO>itemDTOS = itemService.getItemsByActiveStatus(activeStatus,page,size);
        PaginatedResponseItemDTO paginatedResponseItemDTO =itemService.getAllItemsPaginated(page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );

    }


    @GetMapping(
            path="get-all-active-items-paginated",
            params = {"page", "size", "activeState"}
    )
    public ResponseEntity<StandardResponse>getAllActiveItemsPaginated(

            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")int size,
            @RequestParam(value = "activeState")boolean activeState

    ){
        //  List<ItemGetResponseDTO>itemDTOS = itemService.getItemsByActiveStatus(activeStatus,page,size);
        PaginatedResponseItemDTO paginatedResponseItemDTO =itemService.getAllActiveItemsPaginated(page,size,activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );

    }



}
