package com.example.stage.service;

import com.example.stage.entity.Entreprise;

import java.util.List;

public interface EntrepriseService {

    List<Entreprise> getEntreprises();

    Entreprise getEntreprise(int id);

    void addEntreprise(Entreprise entreprise);

    void updateEntreprise(Entreprise entreprise);

    void deleteEntreprise(int id);

}
