package bookProject.DAO;

import bookProject.domain.Book;
import bookProject.model.Cart;
import bookProject.model.Pagination;
import bookProject.util.HibernateUtil;
import org.hibernate.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
public class BookDaoImpl implements BookDao {

    Transaction transaction;
    Book book;

    @Override
    public Book findBook(String code) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("From Book book where book.id =:code")
                    .setParameter("code", code);
             book = (Book) query.getSingleResult();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return book;
    }

    @Override
    public Pagination<Cart> queryBooks(int page, int maxResult, int maxNavigationPage) {
        return queryBooks(page, maxResult, maxNavigationPage, null);
    }

    @Override
    public Pagination<Cart> queryBooks(int page, int maxResult, int maxNavigationPage, String likeName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "Select new " + Cart.class.getName()
                + "(p.code, p.nameBook, p.author,p.description, p.priceBook) " + " from "
                + Book.class.getName() + " p ";
        if (likeName != null && likeName.length() > 0) {
            sql += " Where lower(p.name) like :likeName ";
        }
        sql += " order by p.createDate desc ";
            Query query = session.createQuery(sql);
            if (likeName != null && likeName.length() > 0) {
                query.setParameter("likeName", "%" + likeName.toLowerCase() + "%");
            }
        return new Pagination<Cart>((org.hibernate.query.Query) query, page, maxResult, maxNavigationPage);
    }

    @Override
    public void save(Cart cart) {
        String code = cart.getBookCode();
        Book book = null;
        boolean isNew = false;
        if (code != null) {
            book = this.findBook(code);
        }
        if (book == null) {
            isNew = true;
            book = new Book();
            book.setCreateDate(new Date());
        }
        book.setId(code);
        book.setNameBook(cart.getBookCode());
        book.setPriceBook(cart.getBookPrice());
        if (isNew) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.persist(book);
        }
    }

    @Override
    public List<Book> allBooks(){
        List<Book> booksAll = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("From Book");
            booksAll = (List)query.getResultList();
        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return booksAll;
    }

    @Override
    public List<Book> search(String keyword){
        List<Book> searchBook = new ArrayList<>();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("From Book book where book.nameBook like concat('%',:keyword,'%')")
                    .setParameter("keyword",keyword);
            searchBook = (List)query.getResultList();
        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
        }
        return searchBook;
    }

    @Override
    public void save(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction=session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction=session.beginTransaction();
            session.delete(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}
