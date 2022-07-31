package businessLayer;

import dataAccessLayer.OrderDAO;
import model.Order;
import model.Product;

import java.util.List;

public class OrderBLL {
    public int insertOrder(Order order){
        return OrderDAO.insert(order);
    }
    public List<Order> showOrders(){
        return OrderDAO.showO();
    }
}
