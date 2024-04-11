package com.example.projectwishlist.repository;

import com.example.projectwishlist.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Wishlist wishlist) {
        String sql = "INSERT INTO wishlists (wishlist_name, wishlist_deadline_date, wishlist_description, user_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlist.getWishlistName(), wishlist.getWishlistDeadlineDate(), wishlist.getWishlistDescription(), wishlist.getUserId());
    }


    public List<Wishlist> findByUserId(int userId) {
        String sql = "SELECT * FROM wishlists WHERE user_id = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(Wishlist.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Wishlist findById(int wishlistId) {
        String sql = "SELECT * FROM wishlists WHERE wishlist_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{wishlistId}, new BeanPropertyRowMapper<>(Wishlist.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(Wishlist wishlist) {
        String sql = "UPDATE wishlists SET wishlist_name = ?, wishlist_deadline_date = ?, wishlist_description = ? WHERE wishlist_id = ?";
        jdbcTemplate.update(sql, wishlist.getWishlistName(), wishlist.getWishlistDeadlineDate(), wishlist.getWishlistDescription(), wishlist.getWishlistId());
    }

    public void delete(int wishlistId) {
        String sql = "DELETE FROM wishlists WHERE wishlist_id = ?";
        jdbcTemplate.update(sql, wishlistId);
    }
}
