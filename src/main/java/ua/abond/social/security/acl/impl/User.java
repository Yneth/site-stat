package ua.abond.social.security.acl.impl;

import org.springframework.security.core.GrantedAuthority;
import ua.abond.social.security.acl.Owner;

import java.util.Collection;

public class User
        extends org.springframework.security.core.userdetails.User
        implements Owner<Long> {

    private Long id;

    public User(Long id, String username, String password,
                Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    public User(Long id, String username, String password,
                boolean enabled, boolean accountNonExpired,
                boolean credentialsNonExpired, boolean accountNonLocked,
                Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    @Override
    public Long getOwnerId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
