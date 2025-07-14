package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.CreateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import br.com.devhernandesandrade.event_locator.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventRepository eventRepository;

    public void execute(CreateEventRequest createEventRequest, Long userId) {
        EventEntity eventEntity = EventEntity.builder()
                .name(createEventRequest.getTitle())
                .description(createEventRequest.getDescription())
                .eventDate(createEventRequest.getEventDate())
                .place(createEventRequest.getPlace())
                .enabled(false)
                .deleted(false)
                .capacity(createEventRequest.getCapacity())
                .userId(userId)
                .build();
        this.eventRepository.save(eventEntity);
    }

}
