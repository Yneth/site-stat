package ua.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DAO<Entity> {
    Optional<Entity> getById(long id);
    <S extends Entity> S save(S entity);
    Page<Entity> findAll(Pageable pageable);
    void delete(Entity entity);
}