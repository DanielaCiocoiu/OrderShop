package server;

import server.service.OrderServiceImpl;
import server.service.ProductServiceImpl;
import server.service.UserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) throws RemoteException {
        try {
            // Creez un registru cu rol de DNS care va rula pe portul 4545
            Registry registry = LocateRegistry.createRegistry(4545);
            //obiectul creat de server si accesat remote de Client prin lookup
            // Expun instanta de UserServiceImpl sub cheia de "userService"
            registry.rebind("userService", new UserServiceImpl());
            registry.rebind("productService", new ProductServiceImpl());
            registry.rebind("orderService", new OrderServiceImpl());
            System.out.println("Server -> running");
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Server -> shutdown");
            throw new RuntimeException(e);
        }


    }
}