package com.example.stage.dao;

import com.example.stage.entity.Secteur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecteurDAOImp implements SecteurDAO{

    private final EntityManager entityManager;

    @Autowired
    public SecteurDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Secteur> findAll() {
        TypedQuery<Secteur> query = this.entityManager.createQuery("from Secteur", Secteur.class);
        return query.getResultList();
    }

    @Override
    public Secteur findById(int id) {
        return this.entityManager.find(Secteur.class,id);
    }

    @Override
    public void save(Secteur secteur) {
        this.entityManager.merge(secteur);
    }

    @Override
    public void merge(Secteur secteur) {
        this.entityManager.merge(secteur);
    }

    @Override
    public void deleteById(int id) {
        this.findById(id).getEnterprises().forEach(e->e.setSecteur(null));
        this.entityManager.remove(this.findById(id));
    }
}
