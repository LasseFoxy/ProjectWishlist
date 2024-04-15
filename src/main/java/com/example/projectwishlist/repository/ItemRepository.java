package com.example.projectwishlist.repository;

import com.example.projectwishlist.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void save(Item item) {
    String sql = "INSERT INTO items (item_name, item_price, item_quantity, item_link, item_description, wishlist_id) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, item.getItemName(), item.getItemPrice(), item.getItemQuantity(), item.getItemLink(), item.getItemDescription(), item.getItemWishlistId());
  }

  public List<Item> findByWishlistId(int wishlistID) {
    String sql = "SELECT * FROM items WHERE wishlist_id = ?";
    try {
      return jdbcTemplate.query(sql, new Object[]{wishlistID}, new BeanPropertyRowMapper<>(Item.class));
    } catch (EmptyResultDataAccessException e) {
      return null;
    }
  }

  public Item findByItemId(int itemId){
    String sql = "SELECT * FROM items where item_id = ?";
    try {
      return jdbcTemplate.queryForObject(sql, new Object[]{itemId}, new BeanPropertyRowMapper<>(Item.class));
    } catch (EmptyResultDataAccessException e ){
      return null;
    }
  }

  public Boolean isItemReserved(int itemId){
    String sql = "SELECT item_reserved_status FROM items WHERE item_id=?";
    return jdbcTemplate.queryForObject(sql, Boolean.class, itemId);
  }

  public void toggleReservationStatus(Item item){
    String sql = "UPDATE items SET item_reserved_status = ?, item_reserved_name = ? WHERE item_id = ?";
    jdbcTemplate.update(sql, item.isItemReservedStatus(), item.getItemReservedName(), item.getItemId());
  }

}
