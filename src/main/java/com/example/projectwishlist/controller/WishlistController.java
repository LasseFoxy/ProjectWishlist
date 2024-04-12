package com.example.projectwishlist.controller;

import com.example.projectwishlist.model.Item;
import com.example.projectwishlist.model.User;
import com.example.projectwishlist.model.Wishlist;
import com.example.projectwishlist.service.ItemService;
import com.example.projectwishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final ItemService itemService;

    public WishlistController(WishlistService wishlistService, ItemService itemService) {
        this.wishlistService = wishlistService;
        this.itemService = itemService;
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
    public String wishlistItems(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist selectedWishlist = (Wishlist) session.getAttribute("selectedWishlist");

        if (loggedInUser != null && selectedWishlist != null) {
            if (selectedWishlist.getUserId() == loggedInUser.getUserId()) {
                List<Item> wishlistItems = itemService.getWishlistItems(selectedWishlist.getWishlistId());
                model.addAttribute("wishlist", selectedWishlist);
                model.addAttribute("wishlistItems", wishlistItems);
                return "wishlistItems";
            } else {
                return "redirect:/welcome";
            }
        } else {
            return "redirect:/user/login";
        }
    }
    @GetMapping("/wishlist/edit/{wishlistId}")
    public String showEditWishlistForm(@PathVariable int wishlistId, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);

        if (loggedInUser != null && wishlist != null && wishlist.getUserId() == loggedInUser.getUserId()) {
            model.addAttribute("wishlist", wishlist);
            return "updateWishlist"; // Navnet på din HTML-fil til visning/redigering af ønskesedlen
        } else {
            return "redirect:/welcome"; // Eller en anden side, hvis brugeren ikke har adgang til ønskesedlen
        }
    }

    @PostMapping("/wishlist/update/{wishlistId}")
    public String updateWishlist(@PathVariable int wishlistId, @ModelAttribute Wishlist wishlist) {
        wishlist.setWishlistId(wishlistId);
        wishlistService.update(wishlist);
        return "redirect:/welcome";
    }
}
