package my.rest.application.domain.repository;

import my.rest.application.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author abogaichuk
 */
@Repository
public class InMemoryUsers implements UserRepository {

    private static final List<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.stream()
                .filter(u -> u.getUsername().equals(login))
                .findAny();
    }
}
