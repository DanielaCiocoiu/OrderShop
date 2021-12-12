package client.controller;

import lib.dto.OrderDTO;
import lib.service.OrderService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Collection;

public class OrderController {

    private final OrderService orderService;

    private OrderController() {
        try {
            var registry = LocateRegistry.getRegistry("localhost", 4545);

            orderService = (OrderService) registry.lookup("orderService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder {
        public static final OrderController INSTANCE = new OrderController();
    }

    public static OrderController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void persist(OrderDTO orderDto) {
        try {
            orderService.persist(orderDto);
        } catch (RemoteException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    public Collection<OrderDTO> findByProductId(int productId) {
        try {
            return orderService.findByProductId(productId);
        } catch (RemoteException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }
}
