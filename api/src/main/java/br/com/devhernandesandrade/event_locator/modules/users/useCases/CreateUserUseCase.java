package br.com.devhernandesandrade.event_locator.modules.users.useCases;

import br.com.devhernandesandrade.event_locator.exceptions.ValidationException;
import br.com.devhernandesandrade.event_locator.modules.users.dto.CreateUserRequest;
import br.com.devhernandesandrade.event_locator.modules.users.entities.RoleUser;
import br.com.devhernandesandrade.event_locator.modules.users.entities.UserEntity;
import br.com.devhernandesandrade.event_locator.modules.users.repository.UserRepository;
import br.com.devhernandesandrade.event_locator.utils.EmailValidator;
import br.com.devhernandesandrade.event_locator.utils.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String execute(CreateUserRequest user) {

        if (user.getName() == null || user.getName().isEmpty()
                || user.getEmail() == null || user.getEmail().isEmpty()
                || user.getPassword() == null || user.getPassword().isEmpty()
        ) {
            throw new ValidationException("Dados obrigatórios não informados");
        }

        if (!EmailValidator.isValid(user.getEmail())) {
            throw new ValidationException("E-mail inválido");
        }

        if (!PasswordValidator.isPasswordSecure(user.getPassword())) {
            throw new ValidationException("Senha inválida. A senha deve ter no mínimo 8 caracteres, " +
                    "com pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.");
        }

        Optional<UserEntity> userEntity = this.userRepository.findByEmail(user.getEmail());
        if (userEntity.isPresent()) {
            throw new ValidationException("E-mail já cadastrado");
        }

        var passwordEncrypted = passwordEncoder.encode(user.getPassword());

        UserEntity userToSave = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(passwordEncrypted)
                .role(RoleUser.USER)
                .build();
        userToSave = this.userRepository.save(userToSave);

        return "LinkDoUsuárioCriado: " + userToSave.getId();

    }

}
