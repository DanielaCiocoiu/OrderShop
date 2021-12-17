package lib.service;

import lib.dto.ProductDTO;
import lib.dto.UserDTO;
import lib.dto.UserIdDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ProductService extends Remote {

    void persist(ProductDTO productDto) throws RemoteException;

    Collection<ProductDTO> findAll() throws RemoteException;

    Collection<ProductDTO> findProductsByUser(UserIdDTO userId) throws RemoteException;

   void delete(ProductDTO productDto) throws RemoteException;

}
