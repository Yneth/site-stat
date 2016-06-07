package ua.social.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "USR")
@SequenceGenerator(name = "SEQ", sequenceName =
        "USER_USER_ID_SEQ",
        allocationSize = 1)
@AttributeOverride(name ="id", column = @Column(name = "USER_ID"))
public class User extends AbstractEntity implements UserDetails {
    @Column(name = "USER_NAME", nullable = false)
    private String username;
//    private String password;
//    private String email;

    public User() {
    }

//    public User(long id, String username, String password, String email) {
//        this(username, password, email);
//        this.id = id;
//    }
//
//    public User(String username, String password, String email) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
