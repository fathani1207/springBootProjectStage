package com.example.stage.dao;

import com.example.stage.entity.Enterprise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnterpriseDAOImp implements EnterpriseDAO {

    private final EntityManager entityManager;

    @Autowired
    public EnterpriseDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Enterprise> findAll() {
        TypedQuery<Enterprise> query = this.entityManager.createQuery("FROM Enterprise", Enterprise.class);
        return query.getResultList();
    }

    @Override
    public Enterprise findById(int id) {
        return this.entityManager.find(Enterprise.class, id);
    }

    @Override
    public void save(Enterprise enterprise) {
        this.entityManager.persist(enterprise);
    }

    @Override
    public void merge(Enterprise enterprise) {
        this.entityManager.merge(enterprise);
    }

    @Override
    public void deleteById(int id) {
        this.findById(id).setTitreThatIBuy(null);
        this.entityManager.remove(this.findById(id));
    }
}
