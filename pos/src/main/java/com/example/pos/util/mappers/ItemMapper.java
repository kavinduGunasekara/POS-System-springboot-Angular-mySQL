package com.example.pos.util.mappers;

import com.example.pos.dto.request.ItemSaveRequestDTO;
import com.example.pos.dto.response.ItemGetResponseDTO;
import com.example.pos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO>entityListToDtoList(List<Item>items);
    ItemGetResponseDTO entityToDto(Item item);
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    //Page<Item> ite
    List<ItemGetResponseDTO>LisDTOToPage(Page<Item> items);

    List<ItemGetResponseDTO> pageToList(Page<Item> getAllActiveItemsByPaginated);
}
