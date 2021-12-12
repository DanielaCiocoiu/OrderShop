package lib.service;

import lib.dto.OrderDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface OrderService extends Remote {

    void persist(OrderDTO orderDto) throws RemoteException;

    Collection<OrderDTO> findByProductId(int productId) throws RemoteException;
}
