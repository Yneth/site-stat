package ua.abond.social.dao.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ua.abond.social.dao.SiteSessionDAO;
import ua.abond.social.domain.SiteSession;

import java.util.List;

//@Repository
//public class SiteSessionJpaDAO
//        extends AbstractJpaDAO<SiteSession>
//        implements SiteSessionDAO {
//    public SiteSessionJpaDAO() {
//        super(SiteSession.class);
//    }
//
//    @Override
//    public Page<SiteSession> getBySiteId(Long id, Pageable pageable) {
//        throw new UnsupportedOperationException();
//    }
//
////    @Override
////    public Page<SiteSession> getBySiteIdWithSpec(Long siteId, Specification<SiteSession> spec, Pageable pageable) {
////        throw new UnsupportedOperationException();
////    }
//
//    @Override
//    public List<SiteSession> getBySiteId(Long siteId, Specification<SiteSession> spec) {
//        throw new UnsupportedOperationException();
//    }
//}
