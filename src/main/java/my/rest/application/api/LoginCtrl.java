package my.rest.application.api;

import lombok.extern.slf4j.Slf4j;
import my.rest.application.config.UserAuthentication;
import my.rest.application.domain.entity.Credential;
import my.rest.application.domain.entity.User;
import my.rest.application.service.FakeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import javax.validation.Valid;

/**
 * @author abogaichuk
 */
@RestController
@Slf4j
public class LoginCtrl {

    @Autowired
    private FakeUserDetails authService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@Valid @RequestBody Credential credential) {
        log.info("message");
        User user = (User) authService.loadUserByUsername(credential.getLogin());

        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user));
        String token = RequestContextHolder.currentRequestAttributes().getSessionId();
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
