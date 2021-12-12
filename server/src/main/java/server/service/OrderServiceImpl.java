package server.service;

import lib.dto.OrderDTO;
import lib.service.OrderService;
import server.convert.OrderConvertor;
import server.dao.impl.UserDaoImpl;
import server.dao.interfaces.OrderDao;
import server.dao.interfaces.ProductDao;
import server.dao.impl.OrderDaoImpl;
import server.dao.impl.ProductDaoImpl;
import server.dao.interfaces.UserDao;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.stream.Collectors;

public class OrderServiceImpl extends UnicastRemoteObject
    implements OrderService {

    private final OrderDao orderDao;

    private final ProductDao productDao;
    private final UserDao userDao;

    public OrderServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("ordershopPU");
        var entityManager = entityManagerFactory.createEntityManager();

        orderDao = new OrderDaoImpl(entityManager);
        productDao = new ProductDaoImpl(entityManagerFactory.createEntityManager()); //???????
        userDao = new UserDaoImpl(entityManager);
    }

    @Override
    public void persist(OrderDTO orderDto) throws RemoteException {
        var order = OrderConvertor.convert(orderDto);
        var products = orderDto.getIdProducts().stream()
                .map(productDao::getById)
                .collect(Collectors.toSet());
        order.setProducts(products);
        orderDao.persist(order);
    }

    @Override
    public Collection<OrderDTO> findByProductId(int productId) throws RemoteException {
        return orderDao.findByProductId(productId).stream()
                .map(OrderConvertor::convert)
                .collect(Collectors.toList());
    }
}
