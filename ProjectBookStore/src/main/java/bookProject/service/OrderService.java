package bookProject.service;

import bookProject.model.CartInfo;
import bookProject.model.OrderDetailInfo;
import bookProject.model.OrderInfo;
import bookProject.model.Pagination;

import java.util.List;

public interface OrderService {
    public void saveOrder(CartInfo cartInfo);
    public Pagination<OrderInfo> listOrderInfo(int page,
                                               int maxResult, int maxNavigationPage);
    public OrderInfo getOrderInfo(String orderId);
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
