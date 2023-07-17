package com.example.stage.dao;

import com.example.stage.entity.Entreprise;

import java.util.List;

public interface EntrepriseDAO {

    List<Entreprise> findAll();

    Entreprise findById(int id);

    void save(Entreprise entreprise);

    void merge(Entreprise entreprise);

    void deleteById(int id);

}
