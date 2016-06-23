package ua.abond.social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.abond.social.domain.SocialNetworkSession;

import java.util.Optional;

public interface SocialNetworkSessionService {
    SocialNetworkSession createSession(SocialNetworkSession session);

    void updateSession(SocialNetworkSession session);

    void deleteSession(SocialNetworkSession session);

    Optional<SocialNetworkSession> getById(Long id);

    Page<SocialNetworkSession> getBySocialNetworkId(Long id, Pageable pageable);
}
