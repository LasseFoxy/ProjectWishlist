package com.example.projectwishlist.service;

import com.example.projectwishlist.model.Item;
import com.example.projectwishlist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  // Gemmer en vare i databasen
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

}
