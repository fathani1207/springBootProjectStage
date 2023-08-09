package com.example.stage.service;

import com.example.stage.dao.EnterpriseDAO;
import com.example.stage.entity.Enterprise;
import com.example.stage.entity.Titre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class EnterpriseServiceImp implements EnterpriseService {

    private final EnterpriseDAO enterpriseDAO;

    private final TitreService titreService;

    @Autowired
    public EnterpriseServiceImp(EnterpriseDAO enterpriseDAO, TitreService titreService) {
        this.enterpriseDAO = enterpriseDAO;
        this.titreService = titreService;
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
    public List<Float> getPercentageById(int id, String type){
        List<Float> percentage = new ArrayList<>();
        List<Enterprise> enterpriseList = new ArrayList<>();
        List<Enterprise> enterpriseListDuplicate = new ArrayList<>();
        Enterprise owner = null;
        if (type.equals("providers")){
            enterpriseList = this.enterpriseDAO.getProviders(id);
            enterpriseListDuplicate = this.enterpriseDAO.getProvidersAll(id);
        } else {
            if (type.equals("buyers")){
                enterpriseList =this.enterpriseDAO.getBuyers(id);
                enterpriseListDuplicate =this.enterpriseDAO.getBuyersAll(id);
                owner = this.getEnterprise(id);
            }
        }
        float s;
        for (Enterprise e: enterpriseList) {
            s = 0;
            if (type.equals("providers")){
                for (Enterprise eDuplicated : enterpriseListDuplicate){
                    if (eDuplicated.getId() == e.getId()){
                        s++;
                    }
                }
            } else {
                for (Enterprise eDuplicated : enterpriseListDuplicate) {
                    if (eDuplicated.getId() == e.getId()) {
                        s++;
                    }
                }
            }
            percentage.add(s);
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
