package com.example.stage.dao;

import com.example.stage.entity.Secteur;

import java.util.List;

public interface SecteurDAO {

    List<Secteur> findAll();

    Secteur findById(int id);

    void save(Secteur secteur);

    void merge(Secteur secteur);

    void deleteById(int id);

}
