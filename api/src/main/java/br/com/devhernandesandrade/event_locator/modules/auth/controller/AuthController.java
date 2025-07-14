package br.com.devhernandesandrade.event_locator.modules.auth.controller;

import br.com.devhernandesandrade.event_locator.modules.auth.dto.CreateAuthRequest;
import br.com.devhernandesandrade.event_locator.modules.auth.useCases.CreateAuthUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateAuthUseCase createAuthUseCase;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody @Valid CreateAuthRequest createAuthRequest) {
        try {
            var token = createAuthUseCase.execute(createAuthRequest);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
