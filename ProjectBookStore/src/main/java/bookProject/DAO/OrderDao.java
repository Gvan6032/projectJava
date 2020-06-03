package bookProject.DAO;

import bookProject.domain.Book;
import bookProject.domain.Order;
import bookProject.domain.OrderDetail;
import bookProject.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderDao {
    public void saveOrder(Cart cart);
    public Pagination<OrderInfo> listOrderInfo(int page,
                                               int maxResult, int maxNavigationPage);
    public OrderInfo getOrderInfo(String orderId);
    public List<OrderDetail> listOrderDetailInfos(String orderId);

}