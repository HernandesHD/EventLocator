package br.com.devhernandesandrade.event_locator.modules.users.useCases;

import br.com.devhernandesandrade.event_locator.exceptions.ValidationException;
import br.com.devhernandesandrade.event_locator.modules.users.dto.CreateUserRequest;
import br.com.devhernandesandrade.event_locator.modules.users.entities.RoleUser;
import br.com.devhernandesandrade.event_locator.modules.users.entities.UserEntity;
import br.com.devhernandesandrade.event_locator.modules.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String execute(CreateUserRequest user) {

        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new ValidationException("E-mail já cadastrado");
                });

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
