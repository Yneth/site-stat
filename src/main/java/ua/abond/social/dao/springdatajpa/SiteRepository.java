package ua.abond.social.dao.springdatajpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.domain.Site;

import java.time.LocalDate;
import java.util.Optional;

public interface SiteRepository extends SiteDAO, JpaRepository<Site, Long> {

    @Override
    @Query("select s from Site s where s.user.id = ?1")
    Page<Site> findByUserId(Long id, Pageable pageable);

    @Override
    @Query("select s from Site s where s.user.login = ?1")
    Page<Site> findByUserLogin(String login, Pageable pageable);

    @Override
    @Query("select s from Site s where s.id = ?1")
    Optional<Site> getById(Long id);
}
