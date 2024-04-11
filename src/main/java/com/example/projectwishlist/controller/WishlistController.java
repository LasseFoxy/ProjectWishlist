package com.example.projectwishlist.controller;

import com.example.projectwishlist.model.User;
import com.example.projectwishlist.model.Wishlist;
import com.example.projectwishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlist/create")
    public String showCreateWishlistForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUserId", loggedInUser.getUserId());
            model.addAttribute("wishlist", new Wishlist());
            return "createWishlist";
        } else {
            return "redirect:/user/login";
        }
    }

    @PostMapping("/wishlist/create")
    public String createWishlist(@ModelAttribute Wishlist wishlist, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            wishlist.setUserId(loggedInUser.getUserId());
            wishlistService.save(wishlist);
            return "redirect:/welcome";
        } else {
            return "redirect:/user/login";
        }
    }

    @GetMapping("/wishlist/wishlistItems")
    public String showAddItemForm(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist selectedWishlist = (Wishlist) session.getAttribute("selectedWishlist");

        if (loggedInUser != null && selectedWishlist != null) {
            if (selectedWishlist.getUserId() == loggedInUser.getUserId()) {
                model.addAttribute("wishlist", selectedWishlist);
                return "wishlistItems";
            } else {
                return "redirect:/welcome";
            }
        } else {
            return "redirect:/user/login";
        }
    }

   /* @PostMapping("/wishlist/wishlistItems")
    public String addItemToWishlist(@ModelAttribute Item item, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist selectedWishlist = (Wishlist) session.getAttribute("selectedWishlist");

        if (loggedInUser != null && selectedWishlist != null) {
            if (selectedWishlist.getUserId() == loggedInUser.getUserId()) {
                wishlistService.addItemToWishlist(selectedWishlist, item);
                // Muligvis opdater session med den opdaterede wishlist efter tilføjelse af item
                session.setAttribute("selectedWishlist", selectedWishlist);
                return "redirect:/wishlist";
            }
        }
        return "redirect:/user/login";
    }
    */

}
