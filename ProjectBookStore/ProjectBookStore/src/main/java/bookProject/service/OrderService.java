package bookProject.service;

import bookProject.domain.OrderDetail;
import bookProject.model.*;

import java.util.List;

public interface OrderService {
    public void saveOrder(Cart cart);
    public Pagination<OrderInfo> listOrderInfo(int page,
                                               int maxResult, int maxNavigationPage);
    public OrderInfo getOrderInfo(String orderId);
    public List<OrderDetail> listOrderDetailInfos(String orderId);
}
