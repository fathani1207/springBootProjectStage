package com.example.stage.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "secteur")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "secteur",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Enterprise> enterprisesList;

    public Secteur() {
    }

    public Secteur(String nom) {
        this.nom = nom;
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

    @JsonIgnore
    public List<Enterprise> getEnterprises() {
        return enterprisesList;
    }

    public void setEnterprises(List<Enterprise> enterprises) {
        this.enterprisesList = enterprises;
    }

    public void addSecteur(Enterprise enterprise){
        if (this.enterprisesList == null) {
            this.enterprisesList = new ArrayList<>();
        }
        this.enterprisesList.add(enterprise);
        enterprise.setSecteur(this);
    }

    @Override
    public String toString() {
        return "Secteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
