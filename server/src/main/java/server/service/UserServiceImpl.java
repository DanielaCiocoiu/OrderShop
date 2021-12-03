package server.service;

import lib.dto.UserDTO;
import lib.service.UserService;
import server.convert.UserConvertor;
import server.dao.impl.UserDaoImpl;
import server.dao.interfaces.UserDao;
import server.model.User;

import javax.persistence.Persistence;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

/*    public UserServiceImpl() {
        var emf = Persistence
                .createEntityManagerFactory("ordershopPU");

        this.entityManager = emf.createEntityManager();
    }
    //adaugare username
    public User create(UserDTO userDTO) {
        User u = new User();
        //mapez
        u.setUsername(userDTO.getUsername());
        u.setPassword(userDTO.getPassword());
        //obtin tranzactia si fac persist
        entityManager.getTransaction().begin();
        entityManager.persist(u);
        entityManager.getTransaction().commit();
        return u;
    }
    //caut user dupa userName
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager.createNamedQuery("User.findByUsername", User.class);
        query.setParameter("username", username);
        return query.getResultStream().findFirst();
    }*/

    private final UserDao userDao;

    public UserServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence
                .createEntityManagerFactory("ordershopPU");
        var entityManager = entityManagerFactory.createEntityManager();
        this.userDao = new UserDaoImpl(entityManager);
    }

    @Override
    public boolean create(UserDTO userDTO) throws RemoteException {
        Optional<User> userUsername = userDao.findByUsername(userDTO.getUserId().getUserName());
        if(userUsername.isEmpty()){
            User newUser = UserConvertor.convert(userDTO);
            return userDao.create(newUser);
        }
        throw new NoSuchElementException();
    }

    @Override
    public UserDTO loginWithUsername(String userName, String password) throws RemoteException {
        Optional<User> user = userDao.findByUsername(userName);


        return user.filter(u -> u.getPassword().equals(password))
                .map(UserConvertor::convert)
                .orElseThrow( NoSuchElementException::new);

    }

}
