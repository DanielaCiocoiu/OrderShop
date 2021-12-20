package server.dao.impl;

import server.dao.interfaces.UserDao;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

// Mostenim UnicastRemoteObject pentru a putea expune in retea
// implementarea UserServiceImpl
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean persist(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return entityManager.getTransaction().getRollbackOnly();
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByUsername", User.class);
        namedQuery.setParameter("userName", userName);
        return namedQuery.getResultStream().findFirst();
    }

    @Override
    public Collection<User> findAll() {
        var query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public Optional<User> findByCNP(String CNP) {

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByCNP", User.class);
        namedQuery.setParameter("CNP", CNP);
        return namedQuery.getResultStream().findFirst();
    }

}
