package server.service;

import lib.dto.UserDTO;
import lib.service.UserService;
import server.convert.UserConvertor;
import server.dao.impl.UserDaoImpl;
import server.dao.interfaces.UserDao;
import server.model.User;

import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() throws RemoteException {
        var entityManagerFactory = Persistence.createEntityManagerFactory("ordershopPU");
        var entityManager = entityManagerFactory.createEntityManager();
        this.userDao = new UserDaoImpl(entityManager);
    }

    @Override
    public boolean create(UserDTO userDTO) throws RemoteException {
        Optional<User> userUsername = userDao.findByUsername(userDTO.getUserId().getUserName());
        if(userUsername.isEmpty()){
            User newUser = UserConvertor.convert(userDTO);
            return userDao.persist(newUser);
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

    @Override
    public Collection<UserDTO> findAll() throws RemoteException {
        return userDao.findAll().stream()
                .map(UserConvertor::convert)
                .collect(Collectors.toList());
    }

}


