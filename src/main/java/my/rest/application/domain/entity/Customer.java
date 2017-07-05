package my.rest.application.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.ThreadLocalRandom;


/**
 * @author abogaichuk
 */

@Builder(builderClassName = "LombokBuilder")
@Getter
@ToString
public class Customer {
    private Long id;
    private String firstName, lastName;
    private Position position;
    private City city;
    private int salary;
    private boolean male;

    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    public static class CustomerBuilder extends LombokBuilder {

        public CustomerBuilder() {
            super();
            position(Position.getRandom());
            city(City.getRandom());
            salary(ThreadLocalRandom.current().nextInt(5000, 15000));
            male(ThreadLocalRandom.current().nextInt(10) <= 6);
        }
    }
}
