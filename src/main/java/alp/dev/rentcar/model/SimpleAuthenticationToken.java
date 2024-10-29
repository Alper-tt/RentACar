package alp.dev.rentcar.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

// Bu sınıfı kullanmak yerine JwtRequestFilter sınıfına "org.springframework.security.core.GrantedAuthority" import edildi.
public class SimpleAuthenticationToken extends AbstractAuthenticationToken {

    private final Integer userId;

    public SimpleAuthenticationToken(Integer userId, String userRole) {
        super(Collections.emptyList());
        this.userId = userId;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }
}