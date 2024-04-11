package com.example.projectwishlist.controller;

import com.example.projectwishlist.model.Item;
import com.example.projectwishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {
  @Autowired
  private ItemService itemService;

  @GetMapping("/Ønskelisten")
  public String showItem(Model model) {
    model.addAttribute("item", new Item());
    return "item";
  }

  @PostMapping("/saveItem")
  public String saveItem(@ModelAttribute("item") Item item) {
    // Gemmer det indsendte vareobjekt i databasen
    itemService.saveItem(item);
    return "redirect:/item"; // Omdirigerer tilbage til addItem-formularen
  }

}
