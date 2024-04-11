package com.example.projectwishlist.repository;

import com.example.projectwishlist.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void save(Item item) {
    String sql = "INSERT INTO items (item_name, item_Lastname, item_price, item_description, item_link, item_reservedstatus) VALUES (?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, item.getItemName(), item.getItemLastname(), item.getItemPrice(), item.getItemDescription(), item.getItemLink(), item.isItemReservedstatus());

  }
}
