package ua.social.dao;

import java.util.List;

//public interface DAO<K> {
//    AbstractEntity<K> getById(K id);
//    AbstractEntity<K> update(AbstractEntity<K> entity);
//    AbstractEntity<K> save(AbstractEntity<K> entity);
//    AbstractEntity<K> list(int offset, int maxResults);
//    void deleteById(K id);
//    void delete(AbstractEntity<K> entity);
//}
public interface DAO<Entity> {
    Entity getById(long id);
    Entity update(Entity entity);
    void save(Entity entity);
    List<Entity> list(int startPosition, int maxResults);
    void delete(Entity entity);
}