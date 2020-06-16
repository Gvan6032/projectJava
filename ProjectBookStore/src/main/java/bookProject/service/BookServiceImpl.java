package bookProject.service;

import bookProject.DAO.BookDao;
import bookProject.DAO.BookDaoImpl;
import bookProject.domain.Book;
import bookProject.model.Cart;
import bookProject.model.Pagination;
import bookProject.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BookServiceImpl implements BookService {

    private Transaction transaction = null;
    @Autowired
    BookDao bookDao;

    @Override
    public Book findBook(String code) {
        bookDao = new BookDaoImpl();
        return bookDao.findBook(code);
    }

    @Override
    public Pagination<Cart> queryBooks(int page, int maxResult, int maxNavigationPage) {
        bookDao = new BookDaoImpl();
        return bookDao.queryBooks(page,maxResult,maxNavigationPage);
    }

    @Override
    public Pagination<Cart> queryBooks(int page, int maxResult, int maxNavigationPage, String likeName) {
        bookDao = new BookDaoImpl();
        return bookDao.queryBooks(page,maxResult,maxNavigationPage,likeName);
    }

    @Override
    public void save(Cart cart) {
        bookDao = new BookDaoImpl();
        bookDao.save(cart);
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

    @Override
    public void delete(Book book) {
        bookDao = new BookDaoImpl();
        bookDao.delete(book);
    }
}
