package br.com.devhernandesandrade.event_locator.modules.events.controller;


import br.com.devhernandesandrade.event_locator.modules.events.dto.CreateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.useCases.CreateEventUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCase createEventUseCase;

    @PostMapping("/")
    public ResponseEntity<?> create(HttpServletRequest request, @RequestBody @Valid CreateEventRequest eventRequest) {
        Long userId = (Long) request.getAttribute("userId");
        try {
            this.createEventUseCase.execute(eventRequest, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
