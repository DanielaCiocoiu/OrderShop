package client.controller;

import lib.dto.ProductDTO;
import lib.dto.UserIdDTO;
import lib.service.ProductService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Collection;

public class ProductController {

    private final ProductService productService;

    private ProductController() {
        try {
            var registry = LocateRegistry.getRegistry("localhost", 4545);

            productService = (ProductService) registry.lookup("productService");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    private static final class SingletonHolder {
        public static final ProductController INSTANCE = new ProductController();
    }

      public static ProductController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void persist(ProductDTO productDto) {
        try {
            productService.persist(productDto);
        } catch (RemoteException e) {
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    public Collection<ProductDTO> findAll() {
        try {
            return productService.findAll();
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Collection<ProductDTO> findProductsByUser(UserIdDTO userId) {
        try {
            return productService.findProductsByUser(userId);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void delete(ProductDTO productDTO ){
        try {
           productService.delete(productDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
