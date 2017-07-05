package my.rest.application.domain.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author abogaichuk
 */
public enum Authority implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return toString();
    }
}
