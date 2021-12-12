package server.dao.impl;

import server.dao.interfaces.OrderDao;
import server.model.Order;

import javax.persistence.EntityManager;
import java.util.Collection;

public class OrderDaoImpl implements OrderDao {

    private final EntityManager entityManager;

    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(Order order) {
        entityManager.getTransaction().begin();

        entityManager.merge(order);

        entityManager.getTransaction().commit();
    }

    @Override
    public Collection<Order> findByProductId(int productId) {
        var query = entityManager
                .createQuery("SELECT o FROM Order o JOIN o.products p WHERE p.id = :id", Order.class);

        query.setParameter("id", productId);

        return query.getResultList();
    }
}
