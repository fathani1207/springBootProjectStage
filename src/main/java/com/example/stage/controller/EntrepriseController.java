package com.example.stage.controller;


import com.example.stage.entity.Entreprise;
import com.example.stage.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprise")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping
    public List<Entreprise> getEntreprises(){
        return this.entrepriseService.getEntreprises();
    }

    @GetMapping("{entrepriseId}")
    public Entreprise getEntreprise(@PathVariable("entrepriseId") Integer id){
        return this.entrepriseService.getEntreprise(id);
    }

    @PostMapping
    public void addEntreprise(@RequestBody Entreprise entreprise){
        this.entrepriseService.addEntreprise(entreprise);
    }

    @PutMapping
    public void updateEntreprise(@RequestBody Entreprise entreprise){
        this.entrepriseService.updateEntreprise(entreprise);
    }

    @DeleteMapping("{entrepriseId}")
    public void deleteEntreprise(@PathVariable("entrepriseId") Integer id){
        this.entrepriseService.deleteEntreprise(id);
    }
}
