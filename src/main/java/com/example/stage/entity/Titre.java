package com.example.stage.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "titre")
public class Titre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "valeur")
    private float valeur;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Enterprise owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer")
    private Enterprise buyer;

    public Titre() {
    }

    public Titre(float valeur, Enterprise owner, Enterprise buyer) {
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
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
                ", valeur=" + valeur +
                ", owner=" + owner +
                ", buyer=" + buyer +
                '}';
    }
}
