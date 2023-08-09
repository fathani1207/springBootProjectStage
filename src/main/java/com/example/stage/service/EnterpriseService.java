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

    List<Float> getPercentageById(int id, String type);

    void addEnterprise(Enterprise enterprise);

    void updateEnterprise(Enterprise enterprise);

    void deleteEnterprise(int id);

}
