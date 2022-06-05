package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

public interface UserRepository {
    void save(User user);
    User findUserById(long id);

}
