package ua.abond.social.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq")
    protected long id;

    public AbstractEntity() {
    }

    public AbstractEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
