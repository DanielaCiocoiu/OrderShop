package server.service;

import lib.dto.UserDTO;
import lib.service.UserService;
import server.model.User;
import server.repository.UserRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Optional;


// Mostenim UnicastRemoteObject pentru a putea expune in retea
// implementarea UserServiceImpl
public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() throws RemoteException {

        this.userRepository = new UserRepository();
    }


    @Override
    public int login(UserDTO userDTO) throws RemoteException {
        // caut user dupa username si daca exista filtrez dupa password
        Optional<User> userOptional = userRepository
                .findByUsername(userDTO.getUsername());

        return userOptional

                .filter(user -> user.getPassword().equals(userDTO.getPassword()))
                .map(User::getId) //fac un map pt ca eu vreau id
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public int signin(UserDTO userDTO) throws RemoteException {
        Optional<User> userOptional = userRepository
                .findByUsername(userDTO.getUsername());

        if (userOptional.isEmpty()) { //daca nu gasesc dupa user
            return userRepository.createUsername(userDTO).getId();
        } else {
            throw new IllegalArgumentException(); //username-ul este deja folosit
        }
    }
}
