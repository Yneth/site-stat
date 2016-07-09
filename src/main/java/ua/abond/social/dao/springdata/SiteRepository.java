package ua.abond.social.dao.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SiteDAO;
import ua.abond.social.domain.Site;

public interface SiteRepository extends SiteDAO, JpaRepository<Site, Long> {
    @Override
    @Query("select sn from Site sn where sn.user.id = ?1")
    Page<Site> findByUserId(Long id, Pageable pageable);

    @Override
    @Query("select sn from Site sn where sn.user.login IN ?1")
    Page<Site> findByUserLogin(String login, Pageable pageable);

//    @EntityGraph(value = "Site.sessions", type = EntityGraph.EntityGraphType.LOAD)
//    Optional<Site> findByIdWithSessions(Long id);
//
//    @Override
//    @EntityGraph(value = "Site.sessions", type = EntityGraph.EntityGraphType.LOAD)
//    Optional<Site> getByIdWithSessions(Long id);
//
//    @Override
//    @Query("select distinct sn from Site sn join fetch sn.socialNetworkSessions where sn.id = (:id)")
//    Optional<Site> findByIdFullyFetched(@Param("id") Long id);
}
