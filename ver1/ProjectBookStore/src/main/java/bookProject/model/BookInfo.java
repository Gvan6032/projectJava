package bookProject.model;

import bookProject.domain.Book;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

public class BookInfo {
    private String code;//=id
    private String nameBook;
    private String author;
    private String description;
    private double priceBook;
    private boolean newBook=false;
    private Date dateOfCreation;

    public BookInfo() {
    }

    public BookInfo(Book book) {
        this.code = book.getId();
        this.nameBook = book.getNameBook();
        this.author=book.getAuthor();
        this.description=book.getDescription();
        this.priceBook=book.getPriceBook();
        this.dateOfCreation=book.getCreateDate();
    }

    public BookInfo(String code, String nameBook, String author, String description, double priceBook, Date dateOfCreation) {
        this.code = code;
        this.nameBook = nameBook;
        this.author = author;
        this.description = description;
        this.priceBook = priceBook;
        this.dateOfCreation = dateOfCreation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }

    public boolean isNewBook(){
        return newBook;
    }

    public void setNewBook(boolean newBook) {
        this.newBook = newBook;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}

