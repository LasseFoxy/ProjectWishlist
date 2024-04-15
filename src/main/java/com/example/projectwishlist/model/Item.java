package com.example.projectwishlist.model;


public class Item {

  private int itemId;
  private String itemName;
  private double itemPrice;
  private int itemQuantity;
  private String itemLink;
  private String itemDescription;
  private boolean itemReservedStatus;
  private String itemReservedName;
  private int itemWishlistId;


  public Item() {
  }

  public Item(int itemId, String itemName, double itemPrice, int itemQuantity, String itemLink, String itemDescription, boolean itemReservedStatus, String itemReservedName, int itemWishlistId) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
    this.itemLink = itemLink;
    this.itemDescription = itemDescription;
    this.itemReservedStatus = itemReservedStatus;
    this.itemReservedName = itemReservedName;
    this.itemWishlistId = itemWishlistId;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }

  public int getItemQuantity() {
    return itemQuantity;
  }

  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  public String getItemLink() {
    return itemLink;
  }

  public void setItemLink(String itemLink) {
    this.itemLink = itemLink;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }

  public boolean isItemReservedStatus() {
    return itemReservedStatus;
  }

  public void setItemReservedStatus(boolean itemReservedStatus) {
    this.itemReservedStatus = itemReservedStatus;
  }

  public String getItemReservedName() {
    return itemReservedName;
  }

  public void setItemReservedName(String itemReservedName) {
    this.itemReservedName = itemReservedName;
  }

  public int getItemWishlistId() {
    return itemWishlistId;
  }

  public void setItemWishlistId(int itemWishlistId) {
    this.itemWishlistId = itemWishlistId;
  }

  @Override
  public String toString() {
    return "Item{" +
      "itemId=" + itemId +
      ", itemName='" + itemName + '\'' +
      ", itemPrice=" + itemPrice +
      ", itemQuantity=" + itemQuantity +
      ", itemLink='" + itemLink + '\'' +
      ", itemDescription='" + itemDescription + '\'' +
      ", itemReservedStatus=" + itemReservedStatus +
      ", itemReservedName='" + itemReservedName + '\'' +
      ", itemWishlistId=" + itemWishlistId +
      '}';
  }
}
