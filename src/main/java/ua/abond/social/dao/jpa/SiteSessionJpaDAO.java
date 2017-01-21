package ua.abond.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.SiteSession;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SiteSessionJpaDAO
        extends AbstractJpaDAO<SiteSession>
        implements SiteSessionDAO {
    public SiteSessionJpaDAO() {
        super(SiteSession.class);
    }

    @Override
    public Page<SiteSession> getBySiteId(Long id, Pageable pageable) {
        TypedQuery<SiteSession> query = entityManager.
                createQuery("select ss from SiteSession ss where ss.site.id = :id", SiteSession.class);
        query.setParameter("id", id);

        List<SiteSession> content = query.setFirstResult(pageable.getOffset()).
                setMaxResults(pageable.getPageSize()).
                getResultList();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        countQuery.select(criteriaBuilder.count(countQuery.from(clazz)));

        return new PageImpl<>(content, pageable, entityManager.createQuery(countQuery).getSingleResult());
    }

    @Override
    public Page<SiteSession> findAll(Specification<SiteSession> spec, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<SiteSession> getBySiteIdBetweenDates(Long siteId, LocalDateTime start, LocalDateTime end, Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
