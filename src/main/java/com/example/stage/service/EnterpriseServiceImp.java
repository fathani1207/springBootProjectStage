package com.example.stage.service;

import com.example.stage.dao.EnterpriseDAO;
import com.example.stage.entity.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public class EnterpriseServiceImp implements EnterpriseService {

    private final EnterpriseDAO enterpriseDAO;

    @Autowired
    public EnterpriseServiceImp(EnterpriseDAO enterpriseDAO) {
        this.enterpriseDAO = enterpriseDAO;
    }

    @Override
    @Transactional
    public List<Enterprise> getEnterprises() {
        return this.enterpriseDAO.findAll();
    }

    @Override
    @Transactional
    public Enterprise getEnterprise(int id) {
        return this.enterpriseDAO.findById(id);
    }

    @Override
    @Transactional
    public void addEnterprise(Enterprise enterprise) {
        this.enterpriseDAO.save(enterprise);
    }

    @Override
    @Transactional
    public void updateEnterprise(Enterprise enterprise) {
        this.enterpriseDAO.merge(enterprise);
    }

    @Override
    @Transactional
    public void deleteEnterprise(int id) {
        this.enterpriseDAO.deleteById(id);
    }
}
