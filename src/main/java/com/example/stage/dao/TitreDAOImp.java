package com.example.stage.dao;


import com.example.stage.entity.Titre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TitreDAOImp implements TitreDAO{

    private final EntityManager entityManager;

    @Autowired
    public TitreDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Titre> findAll() {
        TypedQuery<Titre> query = this.entityManager.createQuery("from  Titre ", Titre.class);
        return query.getResultList();
    }

    @Override
    public Titre findById(int id) {
        return this.entityManager.find(Titre.class,id);
    }

    @Override
    public void save(Titre titre) {
        this.entityManager.persist(titre);
    }

    @Override
    public void merge(Titre titre) {
        this.entityManager.merge(titre);
    }

    @Override
    public void deleteById(int id) {
        this.entityManager.remove(this.findById(id));
    }
}
