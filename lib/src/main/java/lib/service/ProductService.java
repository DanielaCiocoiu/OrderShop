package lib.service;

import lib.dto.ProductDTO;
import lib.dto.UserIdDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ProductService extends Remote {

    void persist(ProductDTO productDto) throws RemoteException;

    Collection<ProductDTO> findAll() throws RemoteException;

    Collection<ProductDTO> findByUser(UserIdDTO userId) throws RemoteException;
}
