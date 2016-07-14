package ua.abond.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.abond.social.domain.Site;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.domain.SiteStatistic;

import java.util.Optional;

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
    public Page<SiteStatistic> getStatisticsByUserId(Long userId, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Site> getByUserIdWithSessions(Long id) {
        throw new UnsupportedOperationException();
    }
}
