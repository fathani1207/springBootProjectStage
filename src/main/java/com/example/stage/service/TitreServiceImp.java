package com.example.stage.service;

import com.example.stage.dao.TitreDAO;
import com.example.stage.entity.Titre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TitreServiceImp implements TitreService{

    private final TitreDAO titreDAO;

    @Autowired
    public TitreServiceImp(TitreDAO titreDAO) {
        this.titreDAO = titreDAO;
    }

    @Override
    @Transactional
    public List<Titre> getTitres() {
        return this.titreDAO.findAll();
    }

    @Override
    @Transactional
    public Titre getTitre(int id) {
        return this.titreDAO.findById(id);
    }

    @Override
    @Transactional
    public void addTitre(Titre titre) {
        this.titreDAO.save(titre);
    }

    @Override
    @Transactional
    public void updateTitre(Titre titre) {
        this.titreDAO.merge(titre);
    }

    @Override
    @Transactional
    public void deleteTitre(int id) {
        this.titreDAO.deleteById(id);
    }
}
