package lib.service;

import lib.dto.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {

    boolean create(UserDTO userDTO) throws RemoteException;

    UserDTO loginWithUsername(String userName, String password) throws RemoteException;

   // int signin(UserDTO userDTO) throws RemoteException;
}
