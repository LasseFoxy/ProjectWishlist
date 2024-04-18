package com.example.projectwishlist.repository;

import com.example.projectwishlist.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository klasse som forbinder servicelaget med databasen
@Repository
public class ItemRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  // Gemmer et ønske i databasen
  public void save(Item item) {
    String sql = "INSERT INTO items (item_name, item_price, item_quantity, item_link, item_description, wishlist_id) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, item.getItemName(), item.getItemPrice(), item.getItemQuantity(), item.getItemLink(), item.getItemDescription(), item.getItemWishlistId());
  }

  // Finder et/flere ønsker i databasen ud fra wishlistId og returnerer dem som en liste
  public List<Item> findByWishlistId(int wishlistID) {
    String sql = "SELECT * FROM items WHERE wishlist_id = ?";
    try {
      return jdbcTemplate.query(sql, new Object[]{wishlistID}, new BeanPropertyRowMapper<>(Item.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  // Finder et specifikt ønske i databasen ud fra itemId
  public Item findByItemId(int itemId){
    String sql = "SELECT * FROM items where item_id = ?";
    try {
      return jdbcTemplate.queryForObject(sql, new Object[]{itemId}, new BeanPropertyRowMapper<>(Item.class));
    } catch (EmptyResultDataAccessException e ){
      return null;
    }
  }

  // Viser om et Item står som reserveret i databasen
  public Boolean isItemReserved(int itemId){
    String sql = "SELECT item_reserved_status FROM items WHERE item_id=?";
    return jdbcTemplate.queryForObject(sql, Boolean.class, itemId);
  }

  // Ændrer status for et item til reserveret i databasen
  public void toggleReservationStatus(Item item){
    String sql = "UPDATE items SET item_reserved_status = ?, item_reserved_name = ? WHERE item_id = ?";
    jdbcTemplate.update(sql, item.isItemReservedStatus(), item.getItemReservedName(), item.getItemId());
  }

}
