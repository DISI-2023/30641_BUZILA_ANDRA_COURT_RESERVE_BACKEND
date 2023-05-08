package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

public class ReturnPriceDTO {

    private double price;

    public ReturnPriceDTO(double price) {
        this.price = price;
    }

    public ReturnPriceDTO() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
