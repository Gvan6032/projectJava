package bookProject.service;

import bookProject.DAO.OrderDao;
import bookProject.DAO.OrderDaoImpl;
import bookProject.model.CartInfo;
import bookProject.model.OrderDetailInfo;
import bookProject.model.OrderInfo;
import bookProject.model.Pagination;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao;

    @Override
    public void saveOrder(CartInfo cartInfo) {
        orderDao = new OrderDaoImpl();
        orderDao.saveOrder(cartInfo);
    }

    @Override
    public Pagination<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
        orderDao = new OrderDaoImpl();
        return orderDao.listOrderInfo(page,maxResult,maxNavigationPage);
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        orderDao = new OrderDaoImpl();
        return orderDao.getOrderInfo(orderId);
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        orderDao = new OrderDaoImpl();
        return orderDao.listOrderDetailInfos(orderId);
    }
}
