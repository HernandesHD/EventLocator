package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.CreateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventRepository eventRepository;

    public void execute(CreateEventRequest createEventRequest) {

    }

}
