package ua.abond.social.domain.metamodel;

import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;

// TODO: remove
@StaticMetamodel(SiteSession.class)
public class SiteSession_ {
    public static volatile SingularAttribute<SiteSession, Long> id;
    public static volatile SingularAttribute<SiteSession, Long> duration;
    public static volatile SingularAttribute<SiteSession, LocalDateTime> start;
    public static volatile SingularAttribute<SiteSession, LocalDateTime> end;
    public static volatile SingularAttribute<SiteSession, Site> site;
}
