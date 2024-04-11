package com.example.projectwishlist.controller;

import com.example.projectwishlist.model.User;
import com.example.projectwishlist.model.Wishlist;
import com.example.projectwishlist.service.UserService;
import com.example.projectwishlist.service.WishlistService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistService wishlistService;

    // Viser registreringsformularen
    @GetMapping("/user/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Behandler registreringsformularen
    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User loggedInUser = userService.validateUser(username, password);

        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            return "redirect:/welcome";
        } else {
            model.addAttribute("loginError", "Forkert bruger eller adgangskode");
            return "login";
        }
    }


    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
            List<Wishlist> userWishlists = wishlistService.getUserWishlists(loggedInUser.getUserId());
            model.addAttribute("userWishlists", userWishlists);
            return "welcome";
        } else {
            return "redirect:/user/login";
        }
    }

    @PostMapping("/setSelectedWishlist")
    public String setSelectedWishlist(@RequestParam("wishlistId") int wishlistId, HttpSession session) {
        Wishlist selectedWishlist = wishlistService.getWishlistById(wishlistId);
        if (selectedWishlist != null) {
            session.setAttribute("selectedWishlist", selectedWishlist);
        } else {
            return "welcome";
        }
        return "redirect:/wishlist/wishlistItems";
    }


    @GetMapping("/profile")
    public String showProfilePage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "profile";
        } else {
            return "redirect:/user/login";
        }
    }

    @PostMapping("/profile")
    public String editProfile(@ModelAttribute User user, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        user.setUserId(loggedInUser.getUserId());

        userService.updateUser(user);
        session.setAttribute("loggedInUser", user);
        return "redirect:/welcome";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loggedInUser"); // Fjern brugeren fra sessionen
        return "redirect:/"; // Omdiriger til login-siden
    }

}