package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageRepositoryEntityManagerImp implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Message> findAllByFilm(Film film, Integer offset, Integer limit){
        return entityManager
                .createQuery("SELECT mess FROM Message mess WHERE mess.film = :film ORDER BY mess.dateTimeCreate DESC", Message.class)
                .setParameter("film", film)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public void save(Message message){
        entityManager.persist(message);
    }
}
