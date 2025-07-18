package br.com.devhernandesandrade.event_locator.modules.users.controller;

import br.com.devhernandesandrade.event_locator.exceptions.ValidationException;
import br.com.devhernandesandrade.event_locator.modules.users.dto.CreateUserRequest;
import br.com.devhernandesandrade.event_locator.modules.users.useCases.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        try {
            String result = createUserUseCase.execute(createUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(result);
        } catch (ValidationException e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
