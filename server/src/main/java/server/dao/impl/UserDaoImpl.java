package server.dao.impl;

import lib.dto.UserDTO;
import lib.service.UserService;
import server.dao.interfaces.UserDao;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;
// Mostenim UnicastRemoteObject pentru a putea expune in retea
// implementarea UserServiceImpl
public class UserDaoImpl implements UserDao {


    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean create(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return  entityManager.getTransaction().getRollbackOnly();

    }

    @Override
    public Optional<User> findByUsername(String userName){

        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.findByUsername", User.class);
        namedQuery.setParameter("userName", userName);
        return namedQuery.getResultStream().findFirst();
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
