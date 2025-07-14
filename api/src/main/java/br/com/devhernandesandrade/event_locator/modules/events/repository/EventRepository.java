package br.com.devhernandesandrade.event_locator.modules.events.repository;

import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

}
