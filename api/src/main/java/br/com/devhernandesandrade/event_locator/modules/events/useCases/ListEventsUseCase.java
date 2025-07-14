package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.ListEventsResponse;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListEventsUseCase {

    private final EventRepository eventRepository;

    public ResponseEntity<Page<ListEventsResponse>> execute(Pageable pageable) {
        Page<EventEntity> eventsPage = eventRepository.findByDeletedFalse(pageable);

        Page<ListEventsResponse> listEventsResponsePage = eventsPage.map(event ->
                ListEventsResponse.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .description(event.getDescription())
                        .eventDate(event.getEventDate())
                        .place(event.getPlace())
                        .capacity(event.getCapacity())
                        .build()
        );

        return ResponseEntity.ok(listEventsResponsePage);
    }
}
