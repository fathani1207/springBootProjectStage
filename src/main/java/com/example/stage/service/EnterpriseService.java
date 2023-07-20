package com.example.stage.service;

import com.example.stage.entity.Enterprise;

import java.util.List;

public interface EnterpriseService {

    List<Enterprise> getEnterprises();

    Enterprise getEnterprise(int id);

    void addEnterprise(Enterprise enterprise);

    void updateEnterprise(Enterprise enterprise);

    void deleteEnterprise(int id);

}
