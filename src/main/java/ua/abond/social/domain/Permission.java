package ua.abond.social.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "permission")
@SequenceGenerator(name = "seq", sequenceName = "permission_id_seq", allocationSize = 1)
public class Permission extends AbstractEntity {

    @Size(min = 0, max = 50)
    @Column(length = 50, nullable = false)
    private String permission;

    public Permission() {
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission)) return false;

        Permission that = (Permission) o;

        return getPermission() != null ? getPermission().equals(that.getPermission()) : that.getPermission() == null;

    }

    @Override
    public int hashCode() {
        return getPermission() != null ? getPermission().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permission='" + permission + '\'' +
                '}';
    }
}
