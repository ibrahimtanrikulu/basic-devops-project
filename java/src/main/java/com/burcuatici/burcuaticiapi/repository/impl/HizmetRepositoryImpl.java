package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.repository.HizmetRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class HizmetRepositoryImpl implements HizmetRepository {

    private final EntityManager entityManager;

    public HizmetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Hizmet> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Hizmet.class, id));
    }

    @Override
    public List<Hizmet> findAll() {
        var query =
                entityManager.createQuery(
                        "SELECT h FROM Hizmet h WHERE h.isPasive = false OR h.isPasive = null",
                        Hizmet.class
                );
        return query.getResultList();
    }

    @Override
    public List<Hizmet> findAllByHizmetKategoriId(Long hizmetKategoriId) {
        var query =
                entityManager.createQuery(
                        "SELECT h FROM Hizmet h WHERE h.hizmetKategori.id= :hizmetKategoriId",
                        Hizmet.class
                );
        query.setParameter("hizmetKategoriId", hizmetKategoriId);
        return query.getResultList();
    }

    @Override
    public List<Hizmet> findAllById(List<Long> hizmetIds) {
        var query =
                entityManager.createQuery(
                        "SELECT h FROM Hizmet h WHERE h.id IN ( :hizmetIds)",
                        Hizmet.class
                );
        query.setParameter("hizmetIds", hizmetIds);
        return query.getResultList();
    }


    @Override
    @Transactional
    public Hizmet save(Hizmet yeniHizmet) {
        if (yeniHizmet.getId() == null) {
            entityManager.persist(yeniHizmet);
        } else {
            entityManager.merge(yeniHizmet);
        }
        return yeniHizmet;
    }

    @Override
    @Transactional
    public Hizmet update(Long id, Hizmet guncelHizmet) {
        Hizmet hizmetToUpdate = entityManager.find(Hizmet.class, id);
        if (hizmetToUpdate != null) {
            hizmetToUpdate.setHizmetAdi(guncelHizmet.getHizmetAdi());
            hizmetToUpdate.setHizmetKategori(guncelHizmet.getHizmetKategori());
            return entityManager.merge(hizmetToUpdate);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Hizmet hizmet = entityManager.find(Hizmet.class, id);
        if (hizmet != null) {
            hizmet.setPasive(true);
            entityManager.merge(hizmet);
        }
    }
}
