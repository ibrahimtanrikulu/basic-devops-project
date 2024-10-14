package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.Calisan;
import com.burcuatici.burcuaticiapi.model.entity.Hizmet;
import com.burcuatici.burcuaticiapi.model.entity.request.CreateCalisanRequest;
import com.burcuatici.burcuaticiapi.repository.CalisanRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Transactional
class CalisanRepositoryImpl implements CalisanRepository {

    private EntityManager entityManager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public CalisanRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Calisan> findAll() {
        var query = entityManager.createQuery(
                "SELECT c FROM Calisan c WHERE c.isPasive = false OR c.isPasive = null",
                Calisan.class);
        return query.getResultList();
    }

    @Override
    public Optional<Calisan> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Calisan.class, id));
    }

    @Override
    @Transactional
    public Calisan save(Calisan calisan) {
        if (calisan.getId() == null) {
            entityManager.persist(calisan);
        } else {
            entityManager.merge(calisan);
        }
        return calisan;
    }

    @Override
    @Transactional
    public Calisan update(Long id, CreateCalisanRequest calisan) {
        Calisan calisanToUpdate = entityManager.find(Calisan.class, id);
        if (calisanToUpdate != null) {
            calisanToUpdate.setCalisanAdi(calisan.toCalisan().getCalisanAdi());
            calisanToUpdate.setCalisanSoyad(calisan.toCalisan().getCalisanSoyad());
            calisanToUpdate.setCalisanCinsiyet(calisan.toCalisan().getCalisanCinsiyet());
            calisanToUpdate.setCalisanUnvan(calisan.toCalisan().getCalisanUnvan());
            calisanToUpdate.setCalisanTelNo(calisan.toCalisan().getCalisanTelNo());
            calisanToUpdate.setCalisanEmail(calisan.toCalisan().getCalisanEmail());
            calisanToUpdate.setPasive(calisan.toCalisan().getPasive());
            calisanToUpdate.getHizmetler().clear();
            List<Hizmet> newHizmetler = calisan.getHizmetIds().stream()
                    .map(hizmetId -> entityManager.find(Hizmet.class, hizmetId))
                    .filter(Objects::nonNull).toList();
            calisanToUpdate.getHizmetler().addAll(newHizmetler);
            entityManager.merge(calisanToUpdate);
        }
        return calisanToUpdate;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Calisan calisan = entityManager.find(Calisan.class, id);
        if (calisan != null) {
            calisan.setPasive(true);
            entityManager.merge(calisan);
        }
    }
}
