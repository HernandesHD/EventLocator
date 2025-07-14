package br.com.devhernandesandrade.event_locator.modules.events.useCases;

import br.com.devhernandesandrade.event_locator.modules.events.dto.ListEventsResponse;
import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import br.com.devhernandesandrade.event_locator.modules.events.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListEventsUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private ListEventsUseCase useCase;

    @Test
    void shouldReturnPagedEvents() {
        // 1. Configurar o Pageable
        Pageable pageable = PageRequest.of(0, 10);

        // 2. Criar uma EventEntity para simular o retorno do repositório
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(1L);
        eventEntity.setName("Test Event");
        eventEntity.setDescription("This is a test event.");
        eventEntity.setEventDate(LocalDateTime.now()); // Defina uma data válida
        eventEntity.setPlace("Test Location");
        eventEntity.setCapacity(50);
        // Não precisamos mockar o UserEntity se ele for lazy-loaded e não for serializado diretamente no DTO

        Page<EventEntity> mockRepositoryResponse = new PageImpl<>(List.of(eventEntity), pageable, 1);

        // 3. Simular o comportamento do repositório
        when(eventRepository.findByDeletedFalse(pageable)).thenReturn(mockRepositoryResponse);

        // 4. Executar o use case e capturar o resultado
        // O useCase agora retorna ResponseEntity<Page<ListEventsResponse>>
        ResponseEntity<Page<ListEventsResponse>> resultResponseEntity = useCase.execute(pageable);

        // 5. Verificar o status HTTP da resposta
        assertNotNull(resultResponseEntity);
        assertEquals(200, resultResponseEntity.getStatusCodeValue()); // Verifica se o status é OK (200)

        // 6. Obter o corpo da resposta (a Page de ListEventsResponse)
        Page<ListEventsResponse> resultPage = resultResponseEntity.getBody();
        assertNotNull(resultPage);

        // 7. Verificar as asserções na Page de DTOs
        assertEquals(1, resultPage.getTotalElements());
        assertEquals(1, resultPage.getContent().size());

        // 8. Verificar os detalhes do DTO retornado
        ListEventsResponse firstEvent = resultPage.getContent().get(0);
        assertEquals(1L, firstEvent.getId());
        assertEquals("Test Event", firstEvent.getName());
        assertEquals("This is a test event.", firstEvent.getDescription());
        assertEquals("Test Location", firstEvent.getPlace());
        assertEquals(50, firstEvent.getCapacity());

    }
}
