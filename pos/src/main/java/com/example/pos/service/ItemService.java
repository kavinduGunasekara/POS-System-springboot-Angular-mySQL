package com.example.pos.service;

import com.example.pos.dto.request.ItemSaveRequestDTO;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);
}
