package com.example.projectwishlist.controller;

import com.example.projectwishlist.model.Item;
import com.example.projectwishlist.model.User;
import com.example.projectwishlist.model.Wishlist;
import com.example.projectwishlist.service.ItemService;
import com.example.projectwishlist.service.UserService;
import com.example.projectwishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;
    private final ItemService itemService;
    private final UserService userService;

    public WishlistController(WishlistService wishlistService, ItemService itemService, UserService userService) {
        this.wishlistService = wishlistService;
        this.itemService = itemService;
        this.userService = userService;
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
                int wishlist_id = selectedWishlist.getWishlistId();
                String link = "http://localhost:8080/wishlist/share/" + wishlist_id;
                model.addAttribute("shareLink", link);
                model.addAttribute("wishlist", selectedWishlist);
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
    public String showUpdateWishlistForm(@PathVariable int wishlistId, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist wishlist = wishlistService.getWishlistById(wishlistId);

        if (loggedInUser != null && wishlist != null && wishlist.getUserId() == loggedInUser.getUserId()) {
            model.addAttribute("wishlist", wishlist);
            return "updateWishlist"; // Navnet på vores HTML-fil til visning/redigering af ønskesedlen
        } else {
            return "redirect:/welcome";
        }
    }


    @PostMapping("/wishlist/update/{wishlistId}")
    public String updateWishlist(@PathVariable int wishlistId, @ModelAttribute Wishlist wishlist, @RequestParam("newDeadlineDate") LocalDate newDeadlineDate) {
        Wishlist originalWishlist = wishlistService.getWishlistById(wishlistId);
        // Tjekker om wishlistDeadlineDate er ændret
        if (!newDeadlineDate.equals(originalWishlist.getWishlistDeadlineDate())) {
            wishlist.setWishlistDeadlineDate(newDeadlineDate);
            wishlistService.update(wishlist);
        }
        return "redirect:/welcome";
    }

    @GetMapping("/wishlist/share/{wishlist_id}")
    public String shareWishlist(@PathVariable String wishlist_id, Model model, HttpSession session){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist selectedWishlist = wishlistService.getWishlistById(Integer.parseInt(wishlist_id));
        model.addAttribute("wishlist", selectedWishlist);
        if (loggedInUser != null){
            String link = "http://localhost:8080/wishlist/share/" + wishlist_id;
            model.addAttribute("shareLink", link);
            List<Item> wishlistItems = itemService.getWishlistItems(selectedWishlist.getWishlistId());
            model.addAttribute("wishlist", selectedWishlist);
            model.addAttribute("wishlistItems", wishlistItems);
            return "share";
        } else {
            return "redirect:/wishlist/share/login/" + wishlist_id;
        }

    }

    @PostMapping("/wishlist/share/{wishlist_id}")
    public String reserveItem(@ModelAttribute Item item,@PathVariable String wishlist_id, HttpSession session, Model model){
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Wishlist selectedWishlist = wishlistService.getWishlistById(Integer.parseInt(wishlist_id));
        item.setItemWishlistId(wishlistService.findWishListByItemID(item.getItemId()).getWishlistId());
        if (loggedInUser != null) {
            item.setItemReservedName(userService.getUserById(loggedInUser.getUserId()).getFirstname() + " " + userService.getUserById(loggedInUser.getUserId()).getLastname());
            item.setItemReservedStatus(true);
            itemService.toggleReservationStatus(item);
            List<Item> wishlistItems = itemService.getWishlistItems(selectedWishlist.getWishlistId());
            model.addAttribute("wishlist", selectedWishlist);
            model.addAttribute("wishlistItems", wishlistItems);
            return "share";
        } else {
            model.addAttribute("wishlist", selectedWishlist);
            return "redirect:/shareLogin";
        }
    }

    @GetMapping("/wishlist/share/login/{wishlist_id}")
    public String loginShare(Model model, @PathVariable String wishlist_id){
        boolean numbersOnly = wishlist_id.matches("[0-9]+");
        if (!numbersOnly){
            model.addAttribute("wishlistError", "Delings URL Må Kun Indeholde Tal Efter .../share/");
            return "login";
        }
        model.addAttribute("wishlist_id", wishlist_id);
        Wishlist wishlist = wishlistService.getWishlistById(Integer.parseInt(wishlist_id));
            if (wishlist == null) {
            model.addAttribute("loginError", "Ønskeliste findes ikke.");
            return "login";
        }
        return "shareLogin";
    }

    @PostMapping("/wishlist/share/login/{wishlist_id}")
    public String loginShare(@RequestParam String username, @RequestParam String password,@PathVariable String wishlist_id, HttpSession session, Model model, @ModelAttribute Wishlist wishlist) {
        User loggedInUser = userService.validateUser(username, password);
         if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            String link = "http://localhost:8080/wishlist/share/" + wishlist_id;
            return "redirect:" + link;
        }  else {
            model.addAttribute("loginError", "Brugernavn eller Kodeord er forkert.");
            return "shareLogin";
        }
    }

    @GetMapping("/wishlist/share/register/{wishlist_id}")
    public String registerShare(Model model, @PathVariable String wishlist_id){
        model.addAttribute("wishlist_id", wishlist_id);
        model.addAttribute("user", new User());
        return "shareRegister";
    }

    @PostMapping("/wishlist/share/register/{wishlist_id}")
    public String registerShare(@PathVariable String wishlist_id, @ModelAttribute User user, HttpSession session){
        userService.save(user);
        session.setAttribute("loggedInUser", user);
        String link = "http://localhost:8080/wishlist/share/" + wishlist_id;
        return "redirect:" + link;
    }



}
