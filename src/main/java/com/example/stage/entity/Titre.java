package com.example.stage.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "titre")
public class Titre {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Enterprise owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer")
    private Enterprise buyer;

    public Titre() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonBackReference(value = "owner")
    public Enterprise getOwner() {
        return owner;
    }

    public void setOwner(Enterprise owner) {
        this.owner = owner;
    }

    @JsonBackReference(value = "buyer")
    public Enterprise getBuyer() {
        return buyer;
    }

    public void setBuyer(Enterprise buyer) {
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Titre{" +
                "id=" + id +
                ", owner=" + owner +
                ", buyer=" + buyer +
                '}';
    }
}
