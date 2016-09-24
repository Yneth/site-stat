package ua.abond.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.abond.social.domain.Site;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.web.rest.dto.SiteSummary;

@Repository
public class SiteJpaDAO
        extends AbstractJpaDAO<Site>
        implements SiteDAO {

    public SiteJpaDAO() {
        super(Site.class);
    }

    @Override
    public Page<Site> findByUserLogin(String login, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Site> findByUserId(Long id, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<SiteSummary> getStatisticsByUserId(Long userId, Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
