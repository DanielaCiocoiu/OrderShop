package lib.service;

import lib.dto.UserDTO;
import lib.dto.UserIdDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface UserService extends Remote {

    boolean create(UserDTO userDTO) throws RemoteException;

    UserDTO loginWithUsername(String userName, String password, String CNP) throws RemoteException;

    UserDTO loginWithCNP(String CNP, String password) throws RemoteException;

    Collection<UserDTO> findAll() throws RemoteException;
}
