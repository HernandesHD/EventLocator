package br.com.devhernandesandrade.event_locator.modules.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String name;
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,100}$",
            message = "A senha deve conter ao menos uma letra maiúscula, uma minúscula e um número, com no mínimo 8 caracteres"
    )
    private String password;

}
