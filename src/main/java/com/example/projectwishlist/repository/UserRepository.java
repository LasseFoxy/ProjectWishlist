package com.example.projectwishlist.repository;

import com.example.projectwishlist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = "INSERT INTO users (firstname, lastname, username, email, password) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstname(), user.getLastname(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{usernameOrEmail, usernameOrEmail}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findById(Long userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(User user) {
        String sql;
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, username = ?, password = ? WHERE user_id = ?";
            jdbcTemplate.update(sql, user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getPassword(), user.getUserId());
        } else {
            sql = "UPDATE users SET firstname = ?, lastname = ?, email = ?, username = ? WHERE user_id = ?";
            jdbcTemplate.update(sql, user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getUserId());
        }
    }
}
