package com.example.stage.controller;


import com.example.stage.entity.Titre;
import com.example.stage.service.TitreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titre")
public class TitreController {

    private final TitreService titreService;

    @Autowired
    public TitreController(TitreService titreService) {
        this.titreService = titreService;
    }

    @GetMapping
    public List<Titre> getTitres(){
        return this.titreService.getTitres();
    }

    @GetMapping("{titreId}")
    public Titre getTitre(@PathVariable("titreId") String id){
        return this.titreService.getTitre(id);
    }

    @PostMapping
    public void addTitre(@RequestBody Titre titre){
        this.titreService.addTitre(titre);
    }

    @PutMapping
    public void updateTitre(@RequestBody Titre titre){
        this.titreService.updateTitre(titre);
    }

    @DeleteMapping("{titreId}")
    public void deleteTitre(@PathVariable("titreId") String id){
        this.titreService.deleteTitre(id);
    }


}
