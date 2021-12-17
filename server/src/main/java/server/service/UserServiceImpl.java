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
        Optional<User> userCNP = userDao.findByCNP(userDTO.getUserId().getCNP());

        if(userUsername.isEmpty() && userCNP.isEmpty()){
            User newUser = UserConvertor.convert(userDTO);
            return userDao.persist(newUser);
        }
        throw new NoSuchElementException();
    }


    @Override
    public UserDTO loginWithCNP(String CNP, String password) throws RemoteException {
        Optional<User> user = userDao.findByCNP(CNP);
        return user.filter(u -> u.getPassword().equals(password))
                .map(UserConvertor::convert)
                .orElseThrow( NoSuchElementException::new);
    }

    @Override
    public UserDTO loginWithUsername(String userName, String password, String CNP) throws RemoteException {
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


