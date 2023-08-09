package com.example.stage.service;


import com.example.stage.entity.Titre;

import java.util.List;

public interface TitreService {

    List<Titre> getTitres();

    Titre getTitre(String id);

    void addTitre(Titre titre);

    void updateTitre(Titre titre);

    void deleteTitre(String id);

}
