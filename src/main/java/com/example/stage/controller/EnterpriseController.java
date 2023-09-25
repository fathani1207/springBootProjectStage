package com.example.stage.controller;


import com.example.stage.entity.Enterprise;
import com.example.stage.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;


    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping
    public List<Enterprise> getEnterprises(){
        return this.enterpriseService.getEnterprises();
    }

    @GetMapping("{enterpriseId}")
    public Enterprise getEnterprise(@PathVariable("enterpriseId") Integer id){
        return this.enterpriseService.getEnterprise(id);
    }

    @PostMapping
    public void addEnterprise(@RequestBody Enterprise enterprise){
        this.enterpriseService.addEnterprise(enterprise);
    }

    @PutMapping
    public void updateEnterprise(@RequestBody Enterprise enterprise){
        this.enterpriseService.updateEnterprise(enterprise);
    }

    @DeleteMapping("{enterpriseId}")
    public void deleteEnterprise(@PathVariable("enterpriseId") Integer id){
        this.enterpriseService.deleteEnterprise(id);
    }

    @RequestMapping("/providers/{id}")
    @GetMapping
    public List<Enterprise> getProviders(@PathVariable("id") Integer id){
        return this.enterpriseService.getProviders(id);
    }

    @RequestMapping("/providers/total/{id}")
    @GetMapping
    public float totalProvidersById(@PathVariable("id") int id){
        return this.enterpriseService.totalTitresById(id);
    }

    @RequestMapping("/buyers/{id}")
    @GetMapping
    public List<Enterprise> getBuyer(@PathVariable("id") Integer id){
        return this.enterpriseService.getBuyers(id);
    }

    @RequestMapping("/percentage/{id}")
    @GetMapping
    public Map<String,Float> getPercentageById(@PathVariable("id") int id, @RequestParam String type){
        return this.enterpriseService.getPercentageAll(id,type);
    }

    @RequestMapping("/percentage/sector/{id}")
    @GetMapping
    public Map<String,Float> getPercentageBySector(@PathVariable("id") int id, @RequestParam String type, @RequestParam String sector){
        return this.enterpriseService.getPercentageBySector(id,type,sector);
    }



}
