package bookProject.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String bookCode;
    private String bookName;
    private Double bookPrice;
    private String Quantity;
    private String orderNum;
    private String name;
    private String address;
    private String email;
    private String phone;
    private Double amount;
    private String status;

    public Cart(){

    }

    public Cart(String bookCode, String bookName, Double bookPrice,
                String quantity, String orderNum, String name, String address, String email, String phone) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        Quantity = quantity;
        this.orderNum = orderNum;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.amount = Integer.parseInt(quantity)*bookPrice;
        this.status = "Not paid";
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEmpty() {
        return this.orderNum.isEmpty();
    }

    /*public void addCart(Cart cart, String quantity,String code) {
        Cart line = this.bookCode;

        if (line == null) {
            line = new CartLineInfo();
            line.setQuantity(0);
            line.setBookInfo(bookInfo);
            this.cartLines.add(line);
        }
        int newQuantity = line.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLines.remove(line);
        } else {
            line.setQuantity(newQuantity);
        }
    }*/

    public Cart findCartByCode(String code){
        List<Cart> cartList = new ArrayList<>();
        for (Cart cart: cartList){
            if(cart.getOrderNum().equals(code)){
                return cart;
            }
        }
        return null;
    }

    /*public void updateCart(String code, void quantity) {
        Cart line = null;
        line = line.findCartByCode(code);
        if (line != null) {
               line.setQuantity(quantity);
            }
        }
    }*/

    /*public void removeBook(BookInfo bookInfo) {
        CartLineInfo line = this.findLineByCode(bookInfo.getCode());
        if (line != null) {
            this.cartLines.remove(line);
        }
    }

    public boolean isEmpty() {
        return this.cartLines.isEmpty();
    }

    public boolean isValidCustomer() {
        return this.customerInfo != null && this.customerInfo.isValid();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo line : this.cartLines) {
            quantity += line.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo line : this.cartLines) {
            total += line.getAmount();
        }
        return total;
    }

    public void updateQuantity(Cart cartForm) {
        if (cartForm != null) {
            List<CartLineInfo> lines = cartForm.getCartLines();
            for (CartLineInfo line : lines) {
                this.updateBook(line.getBookInfo().getCode(), line.getQuantity());
            }
        }

    }*/

}
