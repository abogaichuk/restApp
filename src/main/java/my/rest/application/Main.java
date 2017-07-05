package my.rest.application;

import my.rest.application.domain.entity.Authority;
import my.rest.application.domain.entity.User;
import my.rest.application.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

import static java.util.Arrays.*;
import static java.util.Collections.*;

/**
 * @author abogaichuk
 */
@SpringBootApplication
@EnableScheduling
public class Main {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init() {
        userRepository.save(
                new User("admin", "admin", asList(Authority.ROLE_USER, Authority.ROLE_ADMIN)));
        userRepository.save(new User("user", "user", singletonList(Authority.ROLE_USER)));
    }
}
