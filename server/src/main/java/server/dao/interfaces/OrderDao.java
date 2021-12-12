package server.dao.interfaces;

import server.model.Order;

import java.util.Collection;

public interface OrderDao {
    void persist(Order order);

    Collection<Order> findByProductId(int productId);
}
