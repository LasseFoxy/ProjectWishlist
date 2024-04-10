package com.example.projectwishlist.model;

import java.time.LocalDate;

public class Wishlist {
    private int wishlistId;
    private String wishlistName;
    private LocalDate deadlineDate;
    private String description;
    private int userId; // Refererer til brugerens ID

    public Wishlist(){
    }

    public Wishlist(int wishlistId, String wishlistName, LocalDate deadlineDate, String description, int userId) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.deadlineDate = deadlineDate;
        this.description = description;
        this.userId = userId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
