package src.controller.auth.token.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    @Email//-> Email alırken @gmail @hotmail gibi kullanımları denetler.
    @NotBlank(message = "Mail adresi boş geçilemez")
    private String email;
    @NotBlank(message = "token boş geçilemez")
    private String token;

    @Override
    public String toString() {
        return "RefreshTokenRequest{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
