package src.controller.auth.token.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
