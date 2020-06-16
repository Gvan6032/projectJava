package bookProject.DAO;

import bookProject.domain.Book;
import bookProject.model.Cart;
import bookProject.model.Pagination;

import java.util.List;


public interface BookDao {
    public Book findBook(String code);
    public Pagination<Cart> queryBooks(int page,
                                           int maxResult, int maxNavigationPage);
    public Pagination<Cart> queryBooks(int page, int maxResult,
                                           int maxNavigationPage, String likeName);
    public void save(Cart cart);
    public List<Book> allBooks();
    public List<Book> search(String keyword);
    public void save(Book book);
    public void delete(Book book);
}
