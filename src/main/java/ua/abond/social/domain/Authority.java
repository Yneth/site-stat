package ua.abond.social.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authority")
@SequenceGenerator(name = "seq", sequenceName = "authority_id_seq", allocationSize = 1)
public class Authority extends AbstractEntity {
    @NotNull
    @Size(min = 0, max = 50)
    @Column(length = 50, nullable = false)
    private String name;

    @OneToMany
    private List<Permission> permissions = new ArrayList<>();

    public Authority() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;

        Authority that = (Authority) o;

        if (getId() != that.getId()) return false;
        if (getName() != null ? getName().equals(that.getName()) : that.getName() == null) return false;
        if (getPermissions() != null ? getPermissions().equals(that.getPermissions()): that.getPermissions() == null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + Long.hashCode(getId());
        result = 31 * result + permissions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
