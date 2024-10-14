package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.YeniHizmet;
import com.burcuatici.burcuaticiapi.repository.YeniHizmetRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class YeniHizmetRepositoryImpl implements YeniHizmetRepository {

    private final EntityManager entityManager;

    public YeniHizmetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<YeniHizmet> findAll() {
        var query = entityManager.createQuery(
                "SELECT yh FROM YeniHizmet yh WHERE yh.isPasive=false OR yh.isPasive=null",
                YeniHizmet.class);
        return query.getResultList();
    }

    @Override
    public Optional<YeniHizmet> findById(Long id) {
        return Optional.ofNullable(entityManager.find(YeniHizmet.class, id));
    }

    @Override
    @Transactional
    public YeniHizmet save(YeniHizmet yeniHizmet) {
        if (yeniHizmet.getId() == null) {
            entityManager.persist(yeniHizmet);
        } else {
            entityManager.merge(yeniHizmet);
        }
        return yeniHizmet;
    }

    @Override
    @Transactional
    public YeniHizmet update(Long id, YeniHizmet yeniHizmet) {
        YeniHizmet hizmetToUpdate = entityManager.find(YeniHizmet.class, id);
        if (hizmetToUpdate != null) {
            hizmetToUpdate.setHizmetKategoriID(yeniHizmet.getHizmetKategoriID());
            hizmetToUpdate.setHizmetID(yeniHizmet.getHizmetID());
            hizmetToUpdate.setYeniHizmetAdi(yeniHizmet.getYeniHizmetAdi());
            hizmetToUpdate.setAciklama(yeniHizmet.getAciklama());
            hizmetToUpdate.setOrtalamaIslemSuresi(yeniHizmet.getOrtalamaIslemSuresi());
            hizmetToUpdate.setCalisanIds(yeniHizmet.getCalisanIds());
            entityManager.merge(hizmetToUpdate);
        }
        return hizmetToUpdate;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        YeniHizmet hizmet = entityManager.find(YeniHizmet.class, id);
        if (hizmet != null) {
            hizmet.setPasive(true);
            entityManager.merge(hizmet);
        }
    }
}
