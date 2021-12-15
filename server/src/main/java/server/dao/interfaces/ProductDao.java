package server.dao.interfaces;

import lib.dto.ProductDTO;
import lib.dto.UserDTO;
import lib.dto.UserIdDTO;
import server.model.Product;
import server.model.User;
import server.model.UserId;

import java.util.Collection;

public interface ProductDao {
    void persist(Product product);

    Collection<Product> findAll();

    Collection<Product> findProductsByUser(UserIdDTO userId);

    Product getById(int id);

    void  delete(Product product);

   // Product findProductByUser(int id);
}
