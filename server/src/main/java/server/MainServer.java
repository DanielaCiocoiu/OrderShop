package server;

import server.service.UserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) throws RemoteException {

        // Cream un registru cu rol de DNS care va rula pe portul 4545
        Registry registry = LocateRegistry.createRegistry(4545);
//obiectul creat de server si accesat remote de Client prin lookup
        // Expunem instanta de MyServiceImpl sub cheia de "myService"
        registry.rebind("userService", new UserServiceImpl());

    }

    //o aplicatie de client care isi va loga pe contul deja creat
    //o interfata grafica pe client cu username, parola, un buton de login si signin
    //aceasta fereastra face un request catre server, care va vedea in baza de date si va vedea daca exista deja un username cu username-ul respectic
    //o aplicatie distribuita cu 3 nivele de arhitectura implementata  pe rmi

}
