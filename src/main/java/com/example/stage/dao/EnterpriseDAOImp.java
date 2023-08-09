package com.example.stage.dao;

import com.example.stage.entity.Enterprise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
    public List<Enterprise> getProviders(int id) {
        String hql = "select e from Enterprise e join Titre t on e.id = t.owner.id where t.buyer.id = :buyer_id";
        TypedQuery<Enterprise> query = this.entityManager.createQuery(hql, Enterprise.class);
        query.setParameter("buyer_id",id);
        return query.getResultList();
    }

    @Override
    public List<Enterprise> getProvidersAll(int id) {
        String sql = "select e.* from opv.enterprise e join opv.titre t on e.id = t.owner where t.buyer = :buyer_id";
        Query query = this.entityManager.createNativeQuery(sql, Enterprise.class);
        query.setParameter("buyer_id",id);
        return query.getResultList();
    }

    @Override
    public List<Enterprise> getBuyers(int id) {
        String hql = "select e from Enterprise e join Titre t on e.id = t.buyer.id where t.owner.id = :owner_id";
        TypedQuery<Enterprise> query = this.entityManager.createQuery(hql, Enterprise.class);
        query.setParameter("owner_id",id);
        return query.getResultList();
    }

    @Override
    public List<Enterprise> getBuyersAll(int id) {
        String sql = "select e.* from opv.enterprise e join opv.titre t on e.id = t.buyer where t.owner = :owner_id";
        Query query = this.entityManager.createNativeQuery(sql, Enterprise.class);
        query.setParameter("owner_id",id);
        return query.getResultList();
    }

    @Override
    public void save(Enterprise enterprise) {
        this.entityManager.merge(enterprise);
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
