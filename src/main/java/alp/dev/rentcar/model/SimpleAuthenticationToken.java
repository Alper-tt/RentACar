package alp.dev.rentcar.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class SimpleAuthenticationToken extends AbstractAuthenticationToken {

    private final Integer userId;

    public SimpleAuthenticationToken(Integer userId) {
        super(Collections.emptyList());  // Yetki listesi boş bırakılıyor.
        this.userId = userId;
        setAuthenticated(true);  // Token doğrulandıysa, authenticated olarak işaretlenir.
    }

    @Override
    public Object getCredentials() {
        return null;  // JWT'de parola taşınmadığı için null döndürülüyor.
    }

    @Override
    public Object getPrincipal() {
        return userId;  // Kullanıcının kimliği (userId) principal olarak döndürülür.
    }
}