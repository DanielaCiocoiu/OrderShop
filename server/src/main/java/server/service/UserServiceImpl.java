package server.service;

import lib.dto.UserDTO;
import lib.service.UserService;
import server.repository.UserRepository;

import java.rmi.RemoteException;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public int login(UserDTO userDTO) throws RemoteException {
        return 0;
    }

    @Override
    public int signin(UserDTO userDTO) throws RemoteException {
        return 0;
    }
}
