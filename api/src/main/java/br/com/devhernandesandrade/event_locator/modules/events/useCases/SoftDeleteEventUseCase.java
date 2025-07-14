package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoftDeleteEventUseCase {

    private final EventRepository eventRepository;

    public void execute(Long id) {
        EventEntity event = eventRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        event.setDeleted(true);
        eventRepository.save(event);
    }

}
