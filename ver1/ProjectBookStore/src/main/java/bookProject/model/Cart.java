package bookProject.model;

import bookProject.controller.BookController;

public class Cart {
    private String bookCode;
    private String bookName;
    private Double bookPrice;
    private int Quantity;
    private String orderNum;
    private String name;
    private String address;
    private String email;
    private String phone;

    public Cart(){

    }

    public Cart(String bookCode, String bookName, Double bookPrice,
                int quantity, String orderNum, String name, String address, String email, String phone) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        Quantity = quantity;
        this.orderNum = orderNum;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
