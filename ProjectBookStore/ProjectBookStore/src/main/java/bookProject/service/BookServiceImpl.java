package bookProject.service;

import bookProject.DAO.BookDao;
import bookProject.DAO.BookDaoImpl;
import bookProject.domain.Book;
import bookProject.model.BookInfo;
import bookProject.model.Pagination;
import bookProject.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookServiceImpl implements BookService {

    private Transaction transaction = null;
    BookDao bookDao = new BookDaoImpl();

    @Override
    public Book findBook(String code) {
        bookDao = new BookDaoImpl();
        return bookDao.findBook(code);
    }

    @Override
    public BookInfo findBookInfo(String code) {
        bookDao = new BookDaoImpl();
        return bookDao.findBookInfo(code);
    }

    @Override
    public Pagination<BookInfo> queryBooks(int page, int maxResult, int maxNavigationPage) {
        bookDao = new BookDaoImpl();
        return bookDao.queryBooks(page,maxResult,maxNavigationPage);
    }

    @Override
    public Pagination<BookInfo> queryBooks(int page, int maxResult, int maxNavigationPage, String likeName) {
        bookDao = new BookDaoImpl();
        return bookDao.queryBooks(page,maxResult,maxNavigationPage,likeName);
    }

    @Override
    public void save(BookInfo bookInfo) {
        bookDao = new BookDaoImpl();
        bookDao.save(bookInfo);
    }

    @Override
    public List<Book> allBooks() {
        bookDao = new BookDaoImpl();
        return bookDao.allBooks();
    }

    @Override
    public List<Book> search(String keyword){
        bookDao = new BookDaoImpl();
        return bookDao.search(keyword);
    }

    @Override
    public void save(Book book) {
        bookDao = new BookDaoImpl();
        bookDao.save(book);
    }
}
