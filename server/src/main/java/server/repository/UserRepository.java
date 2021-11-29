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
        var emf = Persistence
                .createEntityManagerFactory("ordershopPU");

        this.em = emf.createEntityManager();
    }
    //adaugare username
    public User create(UserDTO userDTO) {
        User u = new User();
        //mapez
        u.setUsername(userDTO.getUsername());
        u.setPassword(userDTO.getPassword());
        //obtin tranzactia si fac persist
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();

        return u;
    }
    //gasire user dupa userName
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);

        query.setParameter("username", username);

        return query.getResultStream().findFirst();
    }
}
