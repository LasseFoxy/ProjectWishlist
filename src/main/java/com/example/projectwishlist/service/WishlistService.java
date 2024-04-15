package com.example.projectwishlist.service;

import com.example.projectwishlist.model.Wishlist;
import com.example.projectwishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public void save(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getUserWishlists(int userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public Wishlist getWishlistById(int wishlistId) {
        return wishlistRepository.findById(wishlistId);
    }

    public void update(Wishlist wishlist) {
        wishlistRepository.update(wishlist);
    }

    public void delete(int wishlistId) {
        wishlistRepository.delete(wishlistId);
    }

    public Wishlist findWishListByItemID(int itemId){
        return wishlistRepository.findWishListByItemID(itemId);
    }
}
