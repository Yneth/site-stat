package ua.social.dao.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.social.dao.SocialNetworkDAO;
import ua.social.domain.SocialNetwork;

public interface SocialNetworkRepository extends SocialNetworkDAO, JpaRepository<SocialNetwork, Long> {
}
