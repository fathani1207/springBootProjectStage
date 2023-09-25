package com.example.stage.service;

import com.example.stage.entity.Secteur;
import java.util.List;

public interface SecteurService {
    List<Secteur> getSecteurs();

    Secteur getSecteur(int id);

    void addSecteur(Secteur secteur);

    void updateSecteur(Secteur secteur);

    void deleteSecteur(int id);
}
