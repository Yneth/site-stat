package ua.abond.social.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.Site;
import ua.abond.social.web.rest.dto.SiteSummary;

// TODO: add documentation
public interface SiteDAO extends DAO<Site> {
    Page<Site> findByUserLogin(String login, Pageable pageable);
    Page<Site> findByUserId(Long id, Pageable pageable);
    Page<SiteSummary> getStatisticsByUserId(Long userId, Pageable pageable);
}
