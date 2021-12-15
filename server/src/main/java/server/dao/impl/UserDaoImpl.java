package server.dao.impl;

import lib.dto.UserDTO;
import server.convert.UserConvertor;
import server.dao.interfaces.UserDao;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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

/*    EntityManager#remove() works only on entities which are managed in the current transaction/context.
    In your case, you're retrieving the entity in an earlier transaction,
    storing it in the HTTP session and then attempting to remove it in a different transaction/context.
            First check if entity manager contains entity, then remove*/

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

   /* private server.service.UserServiceImpl userServiceImpl;

    public UserDaoImpl() throws RemoteException {
        userServiceImpl = new server.service.UserServiceImpl();
    }

    @Override
    // caut user dupa username si daca exista filtrez dupa password
    public int login(UserDTO userDTO) throws RemoteException {
        Optional<User> userOptional = userServiceImpl
                .findByUsername(userDTO.getUsername());

        return userOptional
                .filter(user -> user.getPassword().equals(userDTO.getPassword()))
                .map(User::getId)//fac un map pt ca eu vreau id
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public int signin(UserDTO userDTO) throws RemoteException {
        Optional<User> userOptional = userServiceImpl
                .findByUsername(userDTO.getUsername());

        if (userOptional.isEmpty()) {//daca nu gasesc dupa user
            return userServiceImpl.create(userDTO).getId();
        } else {
            throw new IllegalArgumentException(); //username-ul este deja folosit
        }
    }*/
}
