package server.dao.interfaces;

import lib.dto.UserIdDTO;
import server.model.Product;
import server.model.User;
import server.model.UserId;

import java.util.Collection;

public interface ProductDao {
    void persist(Product product);

    Collection<Product> findAll();

    Collection<Product> findByUser(UserIdDTO userId);

    Product getById(int id);

}
