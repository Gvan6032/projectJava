package bookProject.service;

import bookProject.domain.Book;
import bookProject.model.BookInfo;
import bookProject.model.Pagination;
import org.jboss.logging.Param;

import java.util.List;

public interface BookService {
    public Book findBook(String code);
    public BookInfo findBookInfo(String code) ;
    public Pagination<BookInfo> queryBooks(int page,
                                           int maxResult, int maxNavigationPage);
    public Pagination<BookInfo> queryBooks(int page, int maxResult,
                                           int maxNavigationPage, String likeName);
    public void save(BookInfo bookInfo);
    public List<Book> allBooks();
    public List<Book> search(String keyword);

}
