package com.example.pos.service.impl;

import com.example.pos.dto.paginated.PaginatedResponseItemDTO;
import com.example.pos.dto.request.ItemSaveRequestDTO;
import com.example.pos.dto.response.ItemGetResponseDTO;
import com.example.pos.entity.Customer;
import com.example.pos.entity.Item;
import com.example.pos.entity.enums.MeasuringUnitType;
import com.example.pos.exception.NotFoundException;
import com.example.pos.repo.ItemRepo;
import com.example.pos.service.ItemService;
import com.example.pos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.data.domain.Page;


import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;
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
      //  Item item =modelMapper.map(itemSaveRequestDTO,Item.class);
     Item item = itemMapper.dtoToEntity(itemSaveRequestDTO);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemId() + "saved successfully";
        }else{
            throw new DuplicateKeyException("Already added");
        }

    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b = true;

        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, b);
        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items, new TypeToken<List<ItemGetResponseDTO>>() {
            }.getType());
            return itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item id not active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName) {
        boolean b = true;

        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, b);
        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item id not active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus) {


        List<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus);
        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        }else{
            throw new NotFoundException("Item id not active");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));

        if(items.getSize()<1){
            throw new NotFoundException("NO Data");

        }
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.LisDTOToPage(items),
                itemRepo.countAllByActiveStateEquals(activeStatus)


        );

        return paginatedResponseItemDTO;
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsPaginated(int page, int size) {
        Page<Item>getAllItemsByPaginated= itemRepo.findAll(PageRequest.of(page,size));
        return new  PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllItemsByPaginated),
                itemRepo.count()
        );
    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemsPaginated(int page, int size, boolean activeState) {
        Page<Item>getAllActiveItemsByPaginated= itemRepo.findAllByActiveStateEquals(activeState,PageRequest.of(page,size));
        return new  PaginatedResponseItemDTO(
                itemMapper.pageToList(getAllActiveItemsByPaginated),
                itemRepo.countAllByActiveStateEquals(activeState)
        );

    }

}
