package ua.abond.social.domain;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    protected Long id;

    public AbstractEntity() {
    }

    public AbstractEntity(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
