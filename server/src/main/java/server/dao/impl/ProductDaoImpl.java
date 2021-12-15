package server.dao.impl;

import lib.dto.UserIdDTO;
import server.dao.interfaces.ProductDao;
import server.model.Product;

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
    public Collection<Product> findProductsByUser(UserIdDTO userId) {
        var query = entityManager.createQuery("SELECT p from Product p WHERE p.user.userId.userName = :userName", Product.class);
        query.setParameter("userName", userId.getUserName());
        return query.getResultList();
    }
/*    @Override
    public Product findProductByUser(int userId) {
        var query = entityManager.createQuery("SELECT p from Product p WHERE p.user.userId.userName = :userName", Product.class);
        query.setParameter("userName", userId.getUserName());
        return query.getSingleResult();
    }*/

    @Override
    public Product getById(int id) {
        return entityManager.find(Product.class, id);
    }


    @Override
    public void delete(Product product) {
        entityManager.getTransaction().begin();
        entityManager.find(Product.class, product.getId());
        if (product != null) {
            entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
            entityManager.getTransaction().commit();
        }
    }


}
