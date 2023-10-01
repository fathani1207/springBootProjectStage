package com.example.stage.service;

import com.example.stage.entity.Enterprise;

import java.util.List;
import java.util.Map;

public interface EnterpriseService {

    List<Enterprise> getEnterprises();

    Enterprise getEnterprise(int id);

    List<Enterprise> getProviders(int id);

    float totalTitresById(int id);

    List<Enterprise> getBuyers(int id);

    Map<String,Float> getPercentageAll(int id, String type);

    Map<String,Float> getPercentageBySector(int id, String type, String sector);

    void addEnterprise(Enterprise enterprise);

    void updateEnterprise(Enterprise enterprise);

    void deleteEnterprise(int id);

}
