package ua.social.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<Entity> {
    Optional<Entity> getById(long id);
    Entity update(Entity entity);
    void save(Entity entity);
    List<Entity> list(int startPosition, int maxResults);
    void delete(Entity entity);
}