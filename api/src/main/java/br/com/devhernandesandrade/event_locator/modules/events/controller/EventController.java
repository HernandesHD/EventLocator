package br.com.devhernandesandrade.event_locator.modules.events.controller;


import br.com.devhernandesandrade.event_locator.modules.events.dto.CreateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.dto.UpdateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.useCases.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final ListEventUseCase listEventsUseCase;
    private final GetEventByIdUseCase getEventByIdUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final SoftDeleteEventUseCase softDeleteEventUseCase;

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

    @GetMapping
    public ResponseEntity<?> list(Pageable pageable) {
        return ResponseEntity.ok(listEventsUseCase.execute(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return ResponseEntity.ok(getEventByIdUseCase.execute(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @Valid @RequestBody UpdateEventRequest request) {
        updateEventUseCase.execute(id, request);
        return ResponseEntity.ok("Evento atualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        softDeleteEventUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
