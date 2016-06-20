package ua.social.dao.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;

import java.util.Optional;

public interface SocialNetworkRepository extends SocialNetworkDAO, JpaRepository<SocialNetwork, Long> {
    @Override
    @Query("select sn from SocialNetwork sn where sn.user.id = ?1")
    Page<SocialNetwork> findForUserId(Long id, Pageable pageable);

    @Override
    @Query("select sn from SocialNetwork sn where sn.user.login IN ?1")
    Page<SocialNetwork> findForUserLogin(String login, Pageable pageable);

    @Override
    @Query("select sn from SocialNetwork sn join fetch sn.socialNetworkSessions")
    Optional<SocialNetwork> findByIdWithSessions(Long id);

    @Override
    @Query("select sn from SocialNetwork sn join fetch sn.socialNetworkSessions join fetch sn.user")
    Optional<SocialNetwork> findByIdFullyFetched(Long id);
}
