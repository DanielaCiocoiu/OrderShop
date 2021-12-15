package server.dao.interfaces;

import lib.dto.UserDTO;
import lib.dto.UserIdDTO;
import server.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserDao {

    boolean persist(User user);

    Optional<User> findByUsername(String userName);

    Collection<User> findAll();

 // boolean  delete(UserDTO userId);

  //  Optional<User>delete(User user);
}
