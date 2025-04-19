package com.example.btcpricetracker.model;

public class BtcPriceMessage {
    private double price;
    public BtcPriceMessage() {}
    public BtcPriceMessage(double price) { this.price = price; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
