package my.rest.application.domain.repository;

import my.rest.application.domain.entity.Customer;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author abogaichuk
 */
public interface CustomerRepository {
    void create(Integer count);
    Optional<Customer> getById(Long id);
    Collection<Customer> getAll(Predicate<Customer> filter);
}
