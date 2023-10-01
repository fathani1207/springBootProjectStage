package com.example.stage.service;

import com.example.stage.dao.EnterpriseDAO;
import com.example.stage.entity.Enterprise;
import com.example.stage.entity.Secteur;
import com.example.stage.entity.Titre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EnterpriseServiceImp implements EnterpriseService {

    private final EnterpriseDAO enterpriseDAO;

    private final TitreService titreService;

    private final SecteurService secteurService;

    @Autowired
    public EnterpriseServiceImp(EnterpriseDAO enterpriseDAO, TitreService titreService, SecteurService secteurService) {
        this.enterpriseDAO = enterpriseDAO;
        this.titreService = titreService;
        this.secteurService = secteurService;
    }

    @Override
    @Transactional
    public List<Enterprise> getEnterprises() {
        return this.enterpriseDAO.findAll();
    }

    @Override
    @Transactional
    public Enterprise getEnterprise(int id) {
        return this.enterpriseDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Enterprise> getProviders(int id) {
        return this.enterpriseDAO.getProviders(id);
    }

    @Override
    @Transactional
    public float totalTitresById(int id){
        List<Enterprise> enterpriseList = this.enterpriseDAO.getProvidersAll(id);
        float s = 0;
        for (Enterprise e: enterpriseList) {
            s += e.getPrixTitre();
        }
        return s;
    }

    @Override
    public List<Enterprise> getBuyers(int id) {
        return this.enterpriseDAO.getBuyers(id);
    }

    @Override
    @Transactional
    public Map<String,Float> getPercentageAll(int id, String type){
        Map<String,Float> percentage = new HashMap<>();
        List<Secteur> secteurList = this.secteurService.getSecteurs();
        List<Enterprise> enterpriseList = new ArrayList<>();
        if (type.equals("providers")){
            enterpriseList = this.enterpriseDAO.getProvidersAll(id);
        } else {
            if (type.equals("buyers")){
                enterpriseList =this.enterpriseDAO.getBuyersAll(id);
            }
        }
        float s;
        for (Secteur secteur: secteurList) {
            s = 0;
            for (Enterprise enterprise : enterpriseList){
                if (enterprise.getSecteur().getId() == secteur.getId()){
                    s++;
                }
            }
            percentage.put(secteur.getNom(),s);
        }
        return percentage;
    }

    @Override
    public Map<String, Float> getPercentageBySector(int id, String type, String sector) {
        Map<String,Float> percentage = new HashMap<>();
        List<Enterprise> enterpriseList = new ArrayList<>();
        List<Enterprise> enterpriseListDuplicated = new ArrayList<>();
        if (type.equals("providers")){
            enterpriseListDuplicated = this.enterpriseDAO.getProvidersAll(id);
            enterpriseList = this.enterpriseDAO.getProviders(id);
        } else {
            if (type.equals("buyers")){
                enterpriseListDuplicated =this.enterpriseDAO.getBuyersAll(id);
                enterpriseList = this.enterpriseDAO.getBuyers(id);
            }
        }
        float s;
        for (Enterprise enterprise : enterpriseList) {
            if (enterprise.getSecteur().getNom().equals(sector)){
                s = 0;
                for (Enterprise eDuplicated : enterpriseListDuplicated){
                    if (enterprise.getId() == eDuplicated.getId()){
                        s++;
                    }
                }
                percentage.put(enterprise.getNom(),s);
            }
        }
        return percentage;
    }

    @Override
    @Transactional
    public void addEnterprise(Enterprise enterprise) {
        this.enterpriseDAO.save(enterprise);
    }

    @Override
    @Transactional
    public void updateEnterprise(Enterprise enterprise) {
        this.setOwnerUpdate(enterprise);
        this.setBuyerUpdate(enterprise);
        this.enterpriseDAO.merge(enterprise);
    }

    @Override
    @Transactional
    public void deleteEnterprise(int id) {
        this.enterpriseDAO.deleteById(id);
    }

    private void setOwnerUpdate(Enterprise enterprise){
        if (enterprise.getMyOwnTitres() != null){
            enterprise.getMyOwnTitres().forEach(titre -> titre.setOwner(enterprise));
        }
        if (enterprise.getTitreThatIBuy() != null){
            enterprise.getTitreThatIBuy().forEach(titre -> titre.setOwner(this.titreService.getTitre(titre.getId()).getOwner()));
        }
    }

    private void setBuyerUpdate(Enterprise enterprise){
        Enterprise enterprise1 = this.getEnterprise(enterprise.getId());
        if (enterprise.getTitreThatIBuy().size() < enterprise1.getTitreThatIBuy().size()){
            for (Titre t1 : enterprise1.getTitreThatIBuy()){
                int status = 0;
                for (Titre t : enterprise.getTitreThatIBuy()){
                    if (t1.getId().equals(t.getId())){
                        status = 1;
                        break;
                    }
                }
                if (status == 0){
                    t1.setBuyer(null);
                }
            }
        }
    }
}
