package my.rest.application.domain.repository;

import my.rest.application.domain.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author abogaichuk
 */
@Repository
public class InMemoryCustomers implements CustomerRepository {

    private static final List<Customer> customers = new ArrayList<>();

    private final AtomicLong counter = new AtomicLong();

    @Override
    public void create(Integer count) {
        customers.removeAll(customers);
        customers.addAll(IntStream.range(1, ++count).parallel()
                .mapToObj(i -> Customer.builder()
                        .id(counter.incrementAndGet())
                        .firstName("aaa" + i)
                        .lastName("bbb" + i)
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<Customer> getById(Long id) {
        for (Customer customer : customers) {
            if (Objects.equals(customer.getId(), id))
                return Optional.of(customer);
        }
        return Optional.empty();
    }

    @Override
    public Collection<Customer> getAll(Predicate<Customer> filter) {
        return customers.stream()
                .filter(filter)
                .collect(Collectors.toList());

        /*return customers.stream()
                .filter(customer -> customer.getFirstName().equals(firstName) ||
                        customer.getLastName().equals(lastName) || customer.getCity().equals(city) || customer.getPosition().equals(position))
                .collect(Collectors.toList());*/
    }
}
