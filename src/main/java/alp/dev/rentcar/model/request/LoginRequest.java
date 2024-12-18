package alp.dev.rentcar.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class LoginRequest {
    private String username;
    private String email;
    private String password;
    private String userRole;
}
