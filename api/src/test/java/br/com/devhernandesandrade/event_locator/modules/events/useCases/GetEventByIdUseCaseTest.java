package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEventByIdUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private GetEventByIdUseCase useCase;

    @Test
    void shouldReturnEventIfExists() {
        EventEntity entity = new EventEntity();
        when(eventRepository.findById(4L)).thenReturn(Optional.of(entity));

        EventEntity result = useCase.execute(4L);

        assertEquals(entity, result);
    }

    @Test
    void shouldThrowExceptionIfNotFound() {
        when(eventRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> useCase.execute(4L));
    }
}

