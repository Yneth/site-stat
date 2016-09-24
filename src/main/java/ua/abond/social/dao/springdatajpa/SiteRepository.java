package ua.abond.social.dao.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.domain.Site;
import ua.abond.social.web.rest.dto.SiteSummary;

import java.util.Optional;

public interface SiteRepository extends SiteDAO, JpaRepository<Site, Long> {

    @Override
    @Query("select new Site(s.id, s.name, s.url) from Site s where s.user.id = ?1")
    Page<Site> findByUserId(Long id, Pageable pageable);

    @Override
    @Query("select new Site(s.id, s.name, s.url) from Site as s where s.user.login = ?1")
    Page<Site> findByUserLogin(String login, Pageable pageable);

    @Override
    @Query("select s from Site s where s.id = ?1")
    Optional<Site> getById(Long id);

    @Override
    @Query("select new ua.abond.social.web.rest.dto.SiteSummary(s, sum(ss.duration)) " +
            "from Site s " +
            "inner join SiteSession ss on s.id = ss.site.id " +
            "where s.user.id = ?1 " +
            "group by s.id, s.name, s.url")
    Page<SiteSummary> getStatisticsByUserId(Long userId, Pageable pageable);
}
