package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.Musteri;
import com.burcuatici.burcuaticiapi.repository.MusteriRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class MusteriRepositoryImpl implements MusteriRepository {

    private EntityManager entityManager;

    public MusteriRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Musteri> findAll() {
        TypedQuery<Musteri> query = entityManager.createQuery(
                "SELECT m FROM Musteri m WHERE m.isPasive=false OR m.isPasive = null ",
                Musteri.class);
        return query.getResultList();
    }

    public Optional<Musteri> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Musteri.class, id));
    }

    @Override
    @Transactional
    public Musteri save(Musteri musteri) {
        if (musteri.getId() == null) {
            entityManager.persist(musteri);
        } else {
            entityManager.merge(musteri);
        }
        return musteri;
    }

    @Override
    @Transactional
    public Musteri update(Long id, Musteri yeniMusteri) {
        Musteri musteriToUpdate = entityManager.find(Musteri.class, id);
        if (musteriToUpdate != null) {
            musteriToUpdate.setMusteriAdi(yeniMusteri.getMusteriAdi());
            musteriToUpdate.setMusteriSoyadi(yeniMusteri.getMusteriSoyadi());
            musteriToUpdate.setMusteriCinsiyet(yeniMusteri.getMusteriCinsiyet());
            musteriToUpdate.setMusteriTelNo(yeniMusteri.getMusteriTelNo());
            musteriToUpdate.setMusteriTelNo2(yeniMusteri.getMusteriTelNo2());
            musteriToUpdate.setMusteriEmail(yeniMusteri.getMusteriEmail());
            musteriToUpdate.setMusteriDogumGunu(yeniMusteri.getMusteriDogumGunu());
            musteriToUpdate.setMusteriKaraListe(yeniMusteri.isMusteriKaraListe());

            entityManager.merge(musteriToUpdate);
            return musteriToUpdate;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Musteri musteri = entityManager.find(Musteri.class, id);
        if (musteri != null) {
            musteri.setPasive(true);
            entityManager.merge(musteri);
        }
    }
}
