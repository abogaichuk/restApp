package my.rest.application.domain.repository;

import my.rest.application.domain.entity.User;

import java.util.Optional;

/**
 * @author abogaichuk
 */
public interface UserRepository {
    void save(User user);
    Optional<User> findByLogin(String login);
}
