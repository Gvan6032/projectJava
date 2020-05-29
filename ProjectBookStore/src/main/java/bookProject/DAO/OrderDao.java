package bookProject.DAO;

import bookProject.domain.Book;
import bookProject.domain.Order;
import bookProject.model.CartInfo;
import bookProject.model.OrderDetailInfo;
import bookProject.model.OrderInfo;
import bookProject.model.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderDao {
    public void saveOrder(CartInfo cartInfo);
    public Pagination<OrderInfo> listOrderInfo(int page,
                                               int maxResult, int maxNavigationPage);
    public OrderInfo getOrderInfo(String orderId);
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);

}