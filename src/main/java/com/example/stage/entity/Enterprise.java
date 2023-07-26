package com.example.stage.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "address")
    private String address;

    @Column(name = "num_tel")
    private String numTel;

    @Column(name = "prix_titre")
    private float prixTitre;

    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL)
    private List<Titre> myOwnTitres;

    @OneToMany(mappedBy = "buyer",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Titre> titreThatIBuy;

    public Enterprise(String nom, String address, String numTel) {
        this.nom = nom;
        this.address = address;
        this.numTel = numTel;
    }

    public Enterprise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public float getPrixTitre() {
        return prixTitre;
    }

    public void setPrixTitre(float prixTitre) {
        this.prixTitre = prixTitre;
    }

    @JsonManagedReference(value = "owner")
    public List<Titre> getMyOwnTitres() {
        return myOwnTitres;
    }

    public void setMyOwnTitres(List<Titre> myOwnTitres) {
        this.myOwnTitres = myOwnTitres;
    }

    @JsonManagedReference(value = "buyer")
    public List<Titre> getTitreThatIBuy() {
        return titreThatIBuy;
    }

    public void setTitreThatIBuy(List<Titre> titreThatIBuy) {
        this.titreThatIBuy = titreThatIBuy;
    }

    public void addMyOwnTitre(Titre owner){
        if (this.myOwnTitres == null) {
            this.myOwnTitres = new ArrayList<>();
        }
        this.myOwnTitres.add(owner);
        owner.setOwner(this);
    }

    public void addTitreThatIBuy(Titre buyer){
        if (this.titreThatIBuy == null) {
            this.titreThatIBuy = new ArrayList<>();
        }
        this.titreThatIBuy.add(buyer);
        buyer.setBuyer(this);
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", address='" + address + '\'' +
                ", numTel='" + numTel + '\'' +
                ", prixTitre=" + prixTitre +
                '}';
    }
}
