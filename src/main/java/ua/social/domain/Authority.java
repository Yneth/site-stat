package ua.social.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY")
@SequenceGenerator(name = "SEQ", sequenceName = "AUTHORITY_AUTHORITY_ID_SEQ",
        allocationSize = 1)
@AttributeOverride(name = "id", column = @Column(name = "AUTHORITY_ID"))
public class Authority extends AbstractEntity implements GrantedAuthority {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    public Authority() {
    }

    public Authority(User user, Role authority) {
        this.user = user;
        this.authority = authority;
    }

    public String getAuthority() {
        return authority.toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthority(String authority) {
        this.authority = Role.valueOf(authority);
    }
}
