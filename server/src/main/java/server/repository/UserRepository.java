package server.repository;

import lib.dto.UserDTO;
import server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class UserRepository {

    private EntityManager em;

    public UserRepository() {

        var emf = Persistence.createEntityManagerFactory("ShoppingStoreFinalProjectJava2PU");
        this.em = emf.createEntityManager();
    }

    //adaugare username
    public User createUsername(UserDTO userDTO) {
        User user = new User();

        //mapez
        user.setUsername(userDTO.getUsername());
        user.setUsername(userDTO.getPassword());

        //obtin tranzactia si fac persist
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        return user;
    }

    //gasire user dupa userName
    public Optional<User> findByUsername(String username) {

        TypedQuery<User> query = em.createQuery("User.findByUsername", User.class);
        query.setParameter("username", username);

        return query.getResultStream().findFirst();
    }
}
