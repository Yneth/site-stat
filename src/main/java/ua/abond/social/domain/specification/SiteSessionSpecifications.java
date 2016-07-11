package ua.abond.social.domain.specification;

import org.springframework.data.jpa.domain.Specification;
import ua.abond.social.domain.Site;
import ua.abond.social.domain.SiteSession;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

// TODO: remove
public class SiteSessionSpecifications {
    public static Specification<SiteSession> yesterdaySessions() {
        ZonedDateTime yesterday = ZonedDateTime.now().minusDays(1);
        return sessionsOfDay(yesterday);
    }

    public static Specification<SiteSession> and(Specification<SiteSession> a, Specification<SiteSession> b) {
        return (root, query, cb) -> {
            return cb.and(
                    a.toPredicate(root, query, cb),
                    b.toPredicate(root, query, cb)
            );
        };
    }

    public static Specification<SiteSession> sessionsThisMonth() {
        ZonedDateTime startOfTheCurrentMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withSecond(0);
        ZonedDateTime startOfTheNextMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfNextMonth())
                .withHour(0).withSecond(0);

        return betweenDates(startOfTheCurrentMonth, startOfTheNextMonth);
    }

    public static Specification<SiteSession> sessionsOfDay(ZonedDateTime day) {
        ZonedDateTime thisDay = day.withHour(0).withSecond(0);
        ZonedDateTime nextDay = thisDay.plusDays(1);
        return betweenDates(thisDay, nextDay);
    }

    public static Specification<SiteSession> sessionsOfSite(Site site){
        return (root, query, cb) -> {
            return cb.equal(root.get("site"), site);
        };
    }

    public static Specification<SiteSession> sessionsLastMonth() {
        ZonedDateTime startOfThePrevMonth = ZonedDateTime.now()
                .minusMonths(1)
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withSecond(0);
        ZonedDateTime startOfThisMonth = ZonedDateTime.now()
                .with(TemporalAdjusters.firstDayOfMonth())
                .withHour(0).withSecond(0);

        return betweenDates(startOfThePrevMonth, startOfThisMonth);
    }

    public static Specification<SiteSession> betweenDates(ZonedDateTime start, ZonedDateTime end) {
        return (root, query, cb) -> {
            return cb.and(
                    cb.greaterThanOrEqualTo(root.get("start"), start),
                    cb.lessThan(root.get("end"), end)
            );
        };
    }
}
