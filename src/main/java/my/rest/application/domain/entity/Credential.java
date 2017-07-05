package my.rest.application.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author abogaichuk
 */
@Getter
@Setter
public class Credential {
    @NotEmpty(message = "not.be.empty")
    private String login;
    @NotEmpty(message = "not.be.empty")
    private String password;
}
