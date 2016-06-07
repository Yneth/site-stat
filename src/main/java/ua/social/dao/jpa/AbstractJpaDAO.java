package ua.social.dao.jpa;

import org.springframework.stereotype.Repository;
import ua.social.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public abstract class AbstractJpaDAO<Entity> implements DAO<Entity> {
    @PersistenceContext
    private EntityManager entityManager;

    protected final Class<Entity> clazz;

    public AbstractJpaDAO(Class<Entity> clazz) {
        this.clazz = clazz;
    }

    public Entity getById(long id) {
        return entityManager.find(clazz, id);
    }

    public Entity update(Entity entity) {
        return entityManager.merge(entity);
    }

    public void save(Entity entity) {
        entityManager.persist(entity);
    }

    public List<Entity> list(int startPosition, int maxResults) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> query = builder.createQuery(clazz);
        Root<Entity> from = query.from(clazz);
        query.select(from);
        TypedQuery<Entity> typedQuery = entityManager.createQuery(query);
        return typedQuery.
                setFirstResult(startPosition).
                setMaxResults(maxResults).
                getResultList();
    }

    public void delete(Entity entity) {
        entityManager.remove(entity);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
