package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryEntityManagerImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user){
        entityManager.persist(user);
    }

    @Override
    public User findUserById(long id){
        return entityManager.createQuery("SELECT user FROM User user WHERE user.id =: id",User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
