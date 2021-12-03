package server.dao.interfaces;

import server.model.User;

import java.util.Optional;

public interface UserDao {
    boolean create(User user);

    Optional<User> findByUsername(String userName);


}
