package br.com.devhernandesandrade.event_locator.modules.events.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateEventRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    private String title;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 10, max = 1000, message = "A descrição deve ter entre 10 e 500 caracteres")
    private String description;

    @NotNull(message = "Data é obrigatório")
    @FutureOrPresent(message = "A data do evento deve ser hoje ou no futuro")
    private LocalDateTime eventDate;

    @NotBlank(message = "Local é obrigatório")
    @Size(min = 2, max = 200, message = "O local deve ter entre 2 e 200 caracteres")
    private String place;

    private Integer capacity;

}
