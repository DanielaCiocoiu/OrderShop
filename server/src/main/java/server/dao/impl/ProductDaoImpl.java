package server.dao.impl;

import lib.dto.ProductDTO;
import lib.dto.UserIdDTO;
import server.dao.interfaces.ProductDao;
import server.model.Order;
import server.model.Product;
import server.model.User;
import server.model.UserId;

import javax.persistence.EntityManager;
import java.util.Collection;

public class ProductDaoImpl implements ProductDao {

    private final EntityManager entityManager;

    public ProductDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void persist(Product product) {
        entityManager.getTransaction().begin();

        entityManager.persist(product);

        entityManager.getTransaction().commit();
    }

    @Override
    public Collection<Product> findAll() {
        var query = entityManager.createQuery("SELECT p FROM Product p", Product.class);

        return query.getResultList();
    }

    @Override
    public Collection<Product> findByUser(UserIdDTO userId) {
        var query = entityManager.createQuery("SELECT p from Product p WHERE p.userId.userName = :userName", Product.class);

        return query.getResultList();
    }

    @Override
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }

/*    public Collection<Order> findByProductId(int productId) {
        var query = entityManager
                .createQuery("SELECT o FROM Order o JOIN o.products p WHERE p.id = :id", Order.class);

        query.setParameter("id", productId);

        return query.getResultList();*/
}
