package com.example.projectwishlist.model;


public class Item {

    private int itemId;
  private String itemName;
  private String itemLastname;
  private double itemPrice;
  private String itemDescription;
  private String itemLink;
  private boolean itemReservedstatus;
  private int itemWishlistid;
  private int itemQuantity;

    // Konstruktør
    public Item() {
      this.itemId = id;
      this.itemName = name;
      this.itemLastname = lastname;
      this.itemPrice = price;
      this.itemDescription = description;
      this.itemLink = link;
      this.itemReservedstatus = reservedstatus;
      this.itemWishlistid = wishlistid;
      this.itemQuantity=itemQuantity;
    }

    // Gettere og settere
    public int getId() {
      return itemId;
    }
    public void setId(int id) {
      this.itemId = id;
    }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String name) {
    this.itemName = name;
  }

    public String getItemLastname() {
      return itemLastname;
    }

    public void setItemLastname(String itemLastname) {
      this.itemLastname = itemLastname;
    }

    public double getItemPrice() {
      return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
      this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
      return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
      this.itemDescription = itemDescription;
    }

    public String getItemLink() {
      return itemLink;
    }

    public void setItemLink(String itemLink) {
      this.itemLink = itemLink;
    }

    public boolean isItemReservedstatus() {
      return itemReservedstatus;
    }

    public void setItemReservedstatus(boolean itemReservedstatus) {
      this.itemReservedstatus = itemReservedstatus;
    }

    public int getItemWishlistid() {
      return itemWishlistid;
    }

    public void setItemWishlistid(int itemWishlistid) {
      this.itemWishlistid = itemWishlistid;
    }
  public int getItemQuantity() {
    return itemQuantity;
  }
  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  }


