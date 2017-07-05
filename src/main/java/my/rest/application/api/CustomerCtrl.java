package my.rest.application.api;

import my.rest.application.domain.entity.City;
import my.rest.application.domain.entity.Customer;
import my.rest.application.domain.entity.Position;
import my.rest.application.domain.repository.CustomerRepository;
import my.rest.application.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Predicate;

import static my.rest.application.utils.Utils.isNotNullAndNotEmpty;
import static org.springframework.http.HttpStatus.*;

/**
 * @author abogaichuk
 */
@RestController
public class CustomerCtrl {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/customers/")
    public ResponseEntity create(@RequestParam("count") Integer count) {
        if (count == null || count <= 0)
            return new ResponseEntity(BAD_REQUEST);

        repository.create(count);
        return new ResponseEntity(CREATED);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getOne(@PathVariable("id") Long id) {
        Optional<Customer> customer = repository.getById(id);
        return customer.isPresent() ? new ResponseEntity<>(customer.get(), OK) : new ResponseEntity<>(NOT_FOUND);
    }

    @GetMapping("/customers")
    public ResponseEntity<Collection<Customer>> getAll(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "position", required = false) Position position,
            @RequestParam(value = "city", required = false) City city) {
        List<Predicate<Customer>> predicates = allPredicates(firstName, lastName, city, position);
        Predicate<Customer> composite = predicates.stream().reduce(c -> true, Predicate::and);
        return new ResponseEntity<>(repository.getAll(composite), OK);
    }

    private List<Predicate<Customer>> allPredicates(String firstName, String lastName, City city, Position position) {
        List<Predicate<Customer>> predicates = new ArrayList<>();
        if (isNotNullAndNotEmpty(firstName))
            predicates.add(customer -> customer.getFirstName().equals(firstName));
        if (isNotNullAndNotEmpty(lastName))
            predicates.add(customer -> customer.getLastName().equals(lastName));
        if (city != null)
            predicates.add(customer -> customer.getCity().equals(city));
        if (position != null)
            predicates.add(customer -> customer.getPosition().equals(position));
        return predicates;
    }
}
