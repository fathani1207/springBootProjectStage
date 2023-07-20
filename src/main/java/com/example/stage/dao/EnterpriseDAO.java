package com.example.stage.dao;

import com.example.stage.entity.Enterprise;

import java.util.List;

public interface EnterpriseDAO {

    List<Enterprise> findAll();

    Enterprise findById(int id);

    void save(Enterprise enterprise);

    void merge(Enterprise enterprise);

    void deleteById(int id);

}
