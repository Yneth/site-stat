package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DAO<Entity> {
    Optional<Entity> getById(Long id);

    <S extends Entity> S save(S entity);

    long count();

    Page<Entity> findAll(Pageable pageable);

    void delete(Entity entity);

    void deleteById(Long id);
}