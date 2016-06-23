package ua.abond.social.dao.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.abond.social.dao.SocialNetworkDAO;
import ua.abond.social.domain.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkRepository extends SocialNetworkDAO, JpaRepository<SocialNetwork, Long> {
    @Override
    @Query("select sn from SocialNetwork sn where sn.user.id = ?1")
    Page<SocialNetwork> findByUserId(Long id, Pageable pageable);

    @Override
    @Query("select sn from SocialNetwork sn where sn.user.login IN ?1")
    Page<SocialNetwork> findByUserLogin(String login, Pageable pageable);

    @Override
    @Query("select sn from SocialNetwork sn join fetch sn.socialNetworkSessions")
    Optional<SocialNetwork> findByIdWithSessions(Long id);

    @Override
    @Query("select sn from SocialNetwork sn " +
            "join fetch sn.socialNetworkSessions " +
            "join fetch sn.user u " +
            "join fetch u.authorities " +
            "where sn.id = ?1")
    Optional<SocialNetwork> findByIdFullyFetched(Long id);
}
