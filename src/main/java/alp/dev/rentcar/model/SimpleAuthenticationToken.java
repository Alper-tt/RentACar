package alp.dev.rentcar.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class SimpleAuthenticationToken extends AbstractAuthenticationToken {

    private final Integer userId;
    private final String userRole;

    public SimpleAuthenticationToken(Integer userId, String userRole) {
        super(Collections.emptyList());
        this.userId = userId;
        this.userRole = userRole;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId; //userRole eklenmeli
    }
}