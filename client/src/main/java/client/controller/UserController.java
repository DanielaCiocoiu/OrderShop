package client.controller;

import lib.dto.UserDTO;
import lib.service.UserService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Collection;

public class UserController  implements UserService{

    private UserService userService;

    private static final class SingletonHolder {
        public static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private UserController() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 4545);
            //cauta in registru dupa "bind name".
            userService = (UserService) registry.lookup("userService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(UserDTO userDTO) {
        try {
            return userService.create(userDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO loginWithUsername(String userName, String password, String CNP) {
        try {
            return userService.loginWithUsername(userName, password, CNP);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDTO loginWithCNP(String CNP, String password) throws RemoteException {
        try {
            return userService.loginWithCNP(CNP,password);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Collection<UserDTO> findAll() {
        try {
            return userService.findAll();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
