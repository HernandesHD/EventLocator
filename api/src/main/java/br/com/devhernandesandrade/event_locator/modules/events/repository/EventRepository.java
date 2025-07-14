package br.com.devhernandesandrade.event_locator.modules.events.repository;

import br.com.devhernandesandrade.event_locator.modules.events.entities.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    Page<EventEntity> findByDeletedFalse(Pageable pageable);

    Optional<EventEntity> findByIdAndDeletedFalse(Long id);
}
