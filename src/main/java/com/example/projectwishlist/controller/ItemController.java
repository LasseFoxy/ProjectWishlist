package com.example.projectwishlist.controller;

import com.example.projectwishlist.model.Item;
import com.example.projectwishlist.model.User;
import com.example.projectwishlist.model.Wishlist;
import com.example.projectwishlist.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//Controller-klasse for ønsker (Items)
@Controller
public class ItemController {

  @Autowired
  private ItemService itemService;

// Viser formularen for at oprette et ønske
  @GetMapping("/wishlist/addItem")
  public String showAddItemForm(Model model, HttpSession session) {
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    Wishlist selectedWishlist = (Wishlist) session.getAttribute("selectedWishlist");
    if (loggedInUser != null) {
      if (selectedWishlist != null) {
        model.addAttribute("wishlistId", selectedWishlist.getWishlistId());
        model.addAttribute("item", new Item());
        return "addItem";
      } else {
        return "redirect:/welcome";
      }
    } else {
      return "redirect:/user/login";
    }
  }

// Behandler formularen for at oprette et ønske
  @PostMapping("/saveItem")
  public String saveItem(@ModelAttribute("item") Item item, HttpSession session) {
    User loggedInUser = (User) session.getAttribute("loggedInUser");
    Wishlist selectedWishlist = (Wishlist) session.getAttribute("selectedWishlist");

    if (selectedWishlist != null && loggedInUser != null) {
      item.setItemWishlistId(selectedWishlist.getWishlistId());
      itemService.saveItem(item);
      return "redirect:/wishlist/wishlistItems";
    }
    return "redirect:/welcome";
  }
}
