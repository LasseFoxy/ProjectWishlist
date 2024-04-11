package com.example.projectwishlist.model;

import java.time.LocalDate;

public class Wishlist {
    private int wishlistId;
    private String wishlistName;
    private LocalDate wishlistDeadlineDate;
    private String wishlistDescription;
    private int userId;

    public Wishlist(){
    }

    public Wishlist(int wishlistId, String wishlistName, LocalDate wishlistDeadlineDate, String wishlistDescription, int userId) {
        this.wishlistId = wishlistId;
        this.wishlistName = wishlistName;
        this.wishlistDeadlineDate = wishlistDeadlineDate;
        this.wishlistDescription = wishlistDescription;
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

    public LocalDate getWishlistDeadlineDate() {
        return wishlistDeadlineDate;
    }

    public void setWishlistDeadlineDate(LocalDate wishlistDeadlineDate) {
        this.wishlistDeadlineDate = wishlistDeadlineDate;
    }

    public String getWishlistDescription() {
        return wishlistDescription;
    }

    public void setWishlistDescription(String wishlistDescription) {
        this.wishlistDescription = wishlistDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
