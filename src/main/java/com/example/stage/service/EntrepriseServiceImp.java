package com.example.stage.service;

import com.example.stage.dao.EntrepriseDAO;
import com.example.stage.entity.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class EntrepriseServiceImp implements EntrepriseService{

    private final EntrepriseDAO entrepriseDAO;

    @Autowired
    public EntrepriseServiceImp(EntrepriseDAO entrepriseDAO) {
        this.entrepriseDAO = entrepriseDAO;
    }

    @Override
    @Transactional
    public List<Entreprise> getEntreprises() {
        return this.entrepriseDAO.findAll();
    }

    @Override
    @Transactional
    public Entreprise getEntreprise(int id) {
        return this.entrepriseDAO.findById(id);
    }

    @Override
    @Transactional
    public void addEntreprise(Entreprise entreprise) {
        this.entrepriseDAO.save(entreprise);
    }

    @Override
    @Transactional
    public void updateEntreprise(Entreprise entreprise) {
        this.entrepriseDAO.merge(entreprise);
    }

    @Override
    @Transactional
    public void deleteEntreprise(int id) {
        this.entrepriseDAO.deleteById(id);
    }
}
