package com.example.stage.controller;


import com.example.stage.entity.Enterprise;
import com.example.stage.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}
