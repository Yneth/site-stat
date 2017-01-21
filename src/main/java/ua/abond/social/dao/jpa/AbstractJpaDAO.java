package ua.abond.social.dao.jpa;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.abond.social.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractJpaDAO<Entity> implements DAO<Entity> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected final Class<Entity> clazz;

    public AbstractJpaDAO(Class<Entity> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Optional<Entity> getById(Long id) {
        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public long count() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query = selectCount(builder, query);
        return entityManager.createQuery(query).getResultList().
                stream().
                findFirst().
                orElseThrow(() -> new IllegalStateException("Count query returned no results for " + clazz.getSimpleName()));
    }

    @Override
    public <S extends Entity> S save(S entity) {
        return entityManager.merge(entity);
    }

    @Override
    public Page<Entity> findAll(Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> query = builder.createQuery(clazz);
        Root<Entity> from = query.from(clazz);
        query.select(from);
        TypedQuery<Entity> typedQuery = entityManager.createQuery(query);

        List<Entity> totals = typedQuery.
                setFirstResult(pageable.getOffset()).
                setMaxResults(pageable.getPageSize()).
                getResultList();

//        see org.springframework.data.jpa.repository.query.JpaQueryExecution.doExecute implementation
        Long total = count();
        return new PageImpl<>(totals, pageable, total);
    }

    @Override
    public void delete(Entity entity) {
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    private CriteriaQuery<Long> selectCount(CriteriaBuilder cb, CriteriaQuery<Long> cq) {
        return cq.select(cb.count(cq.from(clazz)));
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
