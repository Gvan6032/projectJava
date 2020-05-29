package bookProject.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    //private static final long serialVersionUID = -1000119078147252957L;
    private String id;
    private Date createDate;
    private byte[] image;
    private String nameBook;
    private String author;
    private String description;
    private Double priceBook;

    public Book() {
    }

    @Id
    @Column(name = "id", length = 20, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createDate", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Lob
    @Column(name = "image", length = Integer.MAX_VALUE, nullable = true)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(name = "nameBook", length = 255, nullable = false)
    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    @Column(name = "author", length = 50, nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "description", length = 255, nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "priceBook")
    public Double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(Double priceBook) {
        this.priceBook = priceBook;
    }
}


