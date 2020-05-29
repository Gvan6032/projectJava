package bookProject.DAO;

import bookProject.domain.Book;
import bookProject.domain.Order;
import bookProject.domain.OrderDetail;
import bookProject.model.*;
import bookProject.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Transactional
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private BookDao bookDao;

    private Transaction transaction = null;
    Order order;

    private int getMaxOrderNum() {
        String sql = "Selec t max(o.orderNum) from " + Order.class.getName() + " o ";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(sql);
        Integer value = (Integer) ((org.hibernate.query.Query) query).uniqueResult();
        if (value == null) {
            return 0;
        }
        return value;
    }

    @Override
    public void saveOrder(CartInfo cartInfo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int orderNum = this.getMaxOrderNum() + 1;
            order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setOrderDate(new Date());
            order.setAmount(cartInfo.getAmountTotal());
            CustomerInfo customerInfo = cartInfo.getCustomerInfo();
            order.setCustomerName(customerInfo.getName());
            order.setCustomerEmail (customerInfo.getEmail());
            order.setCustomerPhone(customerInfo.getPhone());
            order.setCustomerAddress(customerInfo.getAddress());
            session.persist(order);
            List<CartLineInfo> lines = cartInfo.getCartLines();
            for (CartLineInfo line : lines) {
                OrderDetail detail = new OrderDetail();
                detail.setId(UUID.randomUUID().toString());
                detail.setOrder(order);
                detail.setAmount(line.getAmount());
                detail.setPrice(line.getBookInfo().getPriceBook());
                detail.setQuanity(line.getQuantity());
                String code = line.getBookInfo().getCode();
                Book book = this.bookDao.findBook(code);
                detail.setBook(book);
                session.persist(detail);
            }
            cartInfo.setOrderNum(orderNum);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Pagination<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        String sql = "Select new " + OrderInfo.class.getName()//
                + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
                + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
                + Order.class.getName() + " ord "//
                + " order by ord.orderNum desc";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(sql);
        return new Pagination<OrderInfo>((org.hibernate.query.Query) query, page, maxResult, maxNavigationPage);
    }

    public Order findOrder(String orderId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Order> crit = builder.createQuery(Order.class);
        List<Predicate> predicate = new ArrayList<Predicate>();
        Root<Order> order = crit.from(Order.class);
        Predicate condition = builder.like(order.<String>get("id"),orderId);
        predicate.add(condition);
        TypedQuery<Order> query = session.createQuery(crit);
        return query.getSingleResult();
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(),
                order.getAmount(), order.getCustomerName(),
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        String sql = "Select new " + OrderDetailInfo.class.getName() //
                + "(d.id, d.bookCode, d.bookName , d.quanity,d.price,d.amount) "//
                + " from " + OrderDetail.class.getName() + " d "//
                + " where d.order.id = :orderId ";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(sql);
        query.setParameter("orderId", orderId);
        return ((org.hibernate.query.Query) query).list();
    }
}



