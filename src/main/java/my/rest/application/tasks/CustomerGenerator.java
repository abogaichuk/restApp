package my.rest.application.tasks;

import my.rest.application.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author abogaichuk
 */
@Component
public class CustomerGenerator {

    @Autowired
    private CustomerRepository repository;

    @Scheduled(fixedDelay = 360000)
    public void execute() {
        repository.create(ThreadLocalRandom.current().nextInt(10));
    }
}
