package com.example.stage.dao;


import com.example.stage.entity.Titre;

import java.util.List;

public interface TitreDAO {

    List<Titre> findAll();

    Titre findById(String id);

    void save(Titre titre);

    void merge(Titre titre);

    void deleteById(String id);

}
