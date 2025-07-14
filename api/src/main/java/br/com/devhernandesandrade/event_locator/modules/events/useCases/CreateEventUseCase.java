package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.CreateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventRepository eventRepository;

    public void execute(CreateEventRequest createEventRequest) {
        EventEntity eventEntity = EventEntity.builder()
                .name(createEventRequest.getTitle())
                .description(createEventRequest.getDescription())
                .eventDate(createEventRequest.getEventDate())
                .place(createEventRequest.getPlace())
                .enabled(false)
                .deleted(false)
                .capacity(createEventRequest.getCapacity())
                .build();
        this.eventRepository.save(eventEntity);
    }

}
