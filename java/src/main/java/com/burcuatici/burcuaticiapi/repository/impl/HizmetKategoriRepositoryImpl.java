package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.HizmetKategori;
import com.burcuatici.burcuaticiapi.repository.HizmetKategoriRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class HizmetKategoriRepositoryImpl implements HizmetKategoriRepository {

    private final EntityManager entityManager;

    public HizmetKategoriRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<HizmetKategori> findById(Long id) {
        return Optional.ofNullable(entityManager.find(HizmetKategori.class, id));
    }

    @Override
    public List<HizmetKategori> findAll() {
        TypedQuery<HizmetKategori> query = entityManager.createQuery(
                "SELECT hk FROM HizmetKategori hk " +
                        "WHERE hk.isPasive = false OR hk.isPasive = null",
                HizmetKategori.class);
        return query.getResultList();
    }

    @Override
    public List<HizmetKategori> findAllWithHizmetler() {
        TypedQuery<HizmetKategori> query = entityManager.createQuery(
                "SELECT DISTINCT hk FROM HizmetKategori hk " +
                        "LEFT JOIN FETCH hk.hizmetler h " +
                        "WHERE (hk.isPasive = false OR hk.isPasive = null) " +
                        "AND (h.isPasive = false OR h.isPasive = null)",
                HizmetKategori.class);
        return query.getResultList();
    }


    @Override
    @Transactional
    public HizmetKategori save(HizmetKategori yeniHizmetKategori) {
        if (yeniHizmetKategori.getId() == null) {
            entityManager.persist(yeniHizmetKategori);
        } else {
            entityManager.merge(yeniHizmetKategori);
        }
        return yeniHizmetKategori;
    }

    @Override
    @Transactional
    public HizmetKategori update(Long id, HizmetKategori guncelHizmet) {
        HizmetKategori hizmetKategoriToUpdate = entityManager.find(HizmetKategori.class, id);
        if (hizmetKategoriToUpdate != null) {
            hizmetKategoriToUpdate.setHizmetKategoriAdi(guncelHizmet.getHizmetKategoriAdi());
            entityManager.merge(hizmetKategoriToUpdate);
        }
        return hizmetKategoriToUpdate;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        HizmetKategori hizmetKategori = entityManager.find(HizmetKategori.class, id);
        if (hizmetKategori != null) {
            hizmetKategori.setPasive(true);
            entityManager.merge(hizmetKategori);
        }
    }
}
