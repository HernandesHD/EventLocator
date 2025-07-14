package br.com.devhernandesandrade.event_locator.modules.events.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ListEventsResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private String place;
    private Integer capacity;

}
