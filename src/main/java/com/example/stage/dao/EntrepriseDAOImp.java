package com.example.stage.dao;

import com.example.stage.entity.Entreprise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntrepriseDAOImp implements EntrepriseDAO{

    private final EntityManager entityManager;

    @Autowired
    public EntrepriseDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Entreprise> findAll() {
        TypedQuery<Entreprise> query = this.entityManager.createQuery("FROM Entreprise", Entreprise.class);
        return query.getResultList();
    }

    @Override
    public Entreprise findById(int id) {
        return this.entityManager.find(Entreprise.class, id);
    }

    @Override
    public void save(Entreprise entreprise) {
        this.entityManager.persist(entreprise);
    }

    @Override
    public void merge(Entreprise entreprise) {
        this.entityManager.merge(entreprise);
    }

    @Override
    public void deleteById(int id) {
        this.entityManager.remove(this.findById(id));
    }
}
