package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.Randevu;
import com.burcuatici.burcuaticiapi.repository.RandevuRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
class RandevuRepositoryImpl implements RandevuRepository {

    private final EntityManager entityManager;

    public RandevuRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Randevu> findAll() {
        var query = entityManager.createQuery(
                "SELECT r FROM Randevu r  WHERE r.isPasive = false OR r.isPasive = null",
                Randevu.class
        );
        return query.getResultList();
    }

    @Override
    public List<Randevu> findAllByDateRandevu(LocalDateTime start, LocalDateTime end) {
        var query = entityManager.createQuery(
                "SELECT r FROM Randevu r WHERE " +
                        "(r.randevuTarih BETWEEN :start AND :end) " +
                        "AND " +
                        "(r.isPasive = false OR r.isPasive IS NULL)",
                Randevu.class
        );
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }


    @Override
    public Optional<Randevu> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Randevu.class, id));
    }

    @Override
    @Transactional
    public Randevu save(Randevu randevu) {
        if (randevu.getId() == null) {
            entityManager.persist(randevu);
        } else {
            entityManager.merge(randevu);
        }
        return randevu;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Randevu randevu = entityManager.find(Randevu.class, id);
        if (randevu != null) {
            randevu.setPasive(true);
            entityManager.merge(randevu);
        }
    }
}