package com.example.projectwishlist.service;

import com.example.projectwishlist.model.Item;
import com.example.projectwishlist.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  // Gemmer en vare i databasen
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  public List<Item> getWishlistItems(int wishlistID) {
    return itemRepository.findByWishlistId(wishlistID);
  }

  public Boolean isItemReserved(int itemId){
    return itemRepository.isItemReserved(itemId);
  }

  public Item findItemByID(int itemId){
    return itemRepository.findByItemId(itemId);
  }

  public void toggleReservationStatus(Item item){
    itemRepository.toggleReservationStatus(item);
  }

}
