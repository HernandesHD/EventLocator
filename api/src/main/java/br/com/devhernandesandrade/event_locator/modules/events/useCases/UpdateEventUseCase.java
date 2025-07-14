package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.UpdateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEventUseCase {

    private final EventRepository eventRepository;

    public void execute(Long id, UpdateEventRequest request) {
        EventEntity event = eventRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        event.setName(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setPlace(request.getPlace());
        event.setCapacity(request.getCapacity());

        eventRepository.save(event);
    }

}
