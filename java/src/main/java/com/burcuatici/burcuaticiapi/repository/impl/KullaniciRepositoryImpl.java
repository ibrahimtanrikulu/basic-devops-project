package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.Kullanici;
import com.burcuatici.burcuaticiapi.model.entity.request.KullaniciRequest;
import com.burcuatici.burcuaticiapi.repository.KullaniciRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class KullaniciRepositoryImpl implements KullaniciRepository {

    private final EntityManager entityManager;

    public KullaniciRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Kullanici> findAll() {
        TypedQuery<Kullanici> query = entityManager.createQuery(
                "SELECT k FROM Kullanici k",
                Kullanici.class);
        return query.getResultList();
    }

    @Override
    public Optional<Kullanici> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Kullanici.class, id));
    }

    @Override
    public Optional<Kullanici> findByKullaniciTelNo(String kullaniciTelNo) {
        TypedQuery<Kullanici> query = entityManager.createQuery(
                "SELECT k FROM Kullanici k WHERE k.kullaniciTelNo = :kullaniciTelNo",
                Kullanici.class);
        query.setParameter("kullaniciTelNo", kullaniciTelNo);
        return query.getResultList().stream().findFirst();
    }

    @Override
    @Transactional
    public Kullanici save(Kullanici kullanici) {
        if (kullanici.getId() == null) {
            entityManager.persist(kullanici);
        } else {
            entityManager.merge(kullanici);
        }
        return kullanici;
    }

    @Override
    @Transactional
    public Kullanici update(Long id, KullaniciRequest kullanici) {
        Kullanici kullaniciToUpdate = entityManager.find(Kullanici.class, id);
        if (kullaniciToUpdate != null) {
            kullaniciToUpdate.setKullaniciAdi(kullanici.getKullaniciAdi());
            kullaniciToUpdate.setKullaniciSoyadi(kullanici.getKullaniciSoyadi());
            kullaniciToUpdate.setKullaniciTelNo(kullanici.getKullaniciTelNo());
            kullaniciToUpdate.setKullaniciEmail(kullanici.getKullaniciEmail());

            entityManager.merge(kullaniciToUpdate);
        }
        return kullaniciToUpdate;
    }
}
