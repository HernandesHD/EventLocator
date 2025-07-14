package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SoftDeleteEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private SoftDeleteEventUseCase useCase;

    @Test
    void shouldSoftDeleteEvent() {
        EventEntity event = new EventEntity();
        event.setDeleted(false);

        when(eventRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(event));

        useCase.execute(1L);

        verify(eventRepository).save(event);
        assertTrue(event.isDeleted());
    }
}
