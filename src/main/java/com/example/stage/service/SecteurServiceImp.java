package com.example.stage.service;


import com.example.stage.dao.SecteurDAO;
import com.example.stage.entity.Secteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecteurServiceImp implements SecteurService{

    private final SecteurDAO secteurDAO;

    @Autowired
    public SecteurServiceImp(SecteurDAO secteurDAO) {
        this.secteurDAO = secteurDAO;
    }

    @Override
    public List<Secteur> getSecteurs() {
        return this.secteurDAO.findAll();
    }

    @Override
    public Secteur getSecteur(int id) {
        return this.secteurDAO.findById(id);
    }

    @Override
    public void addSecteur(Secteur secteur) {
        this.secteurDAO.save(secteur);
    }

    @Override
    public void updateSecteur(Secteur secteur) {
        this.secteurDAO.merge(secteur);
    }

    @Override
    public void deleteSecteur(int id) {
        this.secteurDAO.deleteById(id);
    }
}
