package bookProject.service;

import bookProject.DAO.OrderDao;
import bookProject.DAO.OrderDaoImpl;
import bookProject.domain.OrderDetail;
import bookProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.soap.Addressing;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;


    @Override
    public void saveOrder(Cart cart) {
        orderDao = new OrderDaoImpl();
        orderDao.saveOrder(cart);
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
    public List<OrderDetail> listOrderDetailInfos(String orderId) {
        orderDao = new OrderDaoImpl();
        return orderDao.listOrderDetailInfos(orderId);
    }
}
