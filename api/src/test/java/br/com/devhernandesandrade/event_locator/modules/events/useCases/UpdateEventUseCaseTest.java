package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.UpdateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private UpdateEventUseCase useCase;

    @Test
    void shouldUpdateEventSuccessfully() {
        EventEntity event = new EventEntity();
        event.setId(4L);

        UpdateEventRequest request = new UpdateEventRequest();
        request.setTitle("Novo título");
        request.setDescription("Nova descrição");
        request.setEventDate(LocalDateTime.now());
        request.setPlace("Novo local");
        request.setCapacity(200);

        when(eventRepository.findByIdAndDeletedFalse(4L)).thenReturn(Optional.of(event));

        useCase.execute(4L, request);

        verify(eventRepository).save(event);
        assertEquals("Novo título", event.getName());
    }
}

