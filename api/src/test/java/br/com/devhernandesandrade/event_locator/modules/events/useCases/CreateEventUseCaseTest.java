package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.CreateEventRequest;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import br.com.devhernandesandrade.event_locator.modules.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CreateEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateEventUseCase useCase;

    @Test
    void shouldSaveEventSuccessfully() {
        CreateEventRequest request = new CreateEventRequest();
        request.setTitle("Evento Teste");
        request.setDescription("Descrição");
        request.setEventDate(LocalDateTime.now());
        request.setPlace("Local");
        request.setCapacity(100);

        Long userId = 1L;

        useCase.execute(request, userId);

        ArgumentCaptor<EventEntity> captor = ArgumentCaptor.forClass(EventEntity.class);
        verify(eventRepository).save(captor.capture());

        EventEntity saved = captor.getValue();
        assertEquals("Evento Teste", saved.getName());
        assertEquals(userId, saved.getUserId());
    }

}
