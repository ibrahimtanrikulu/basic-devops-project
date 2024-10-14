package com.burcuatici.burcuaticiapi.repository.impl;

import com.burcuatici.burcuaticiapi.model.entity.Sms;
import com.burcuatici.burcuaticiapi.repository.SmsRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
class SmsRepositoryImpl implements SmsRepository {

    private final EntityManager entityManager;

    public SmsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Sms> findAll() {
        var query = entityManager.createQuery(
                "SELECT s FROM Sms s WHERE s.isPasive=false OR s.isPasive=null",Sms.class
        );
        return query.getResultList();
    }

    @Override
    @Transactional
    public Sms save(Sms sms) {
        if (sms.getId() == null) {
            entityManager.persist(sms);
        } else {
            entityManager.merge(sms);
        }
        return sms;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
       Sms sms = entityManager.find(Sms.class,id);
       if (sms != null) {
           sms.setPasive(true);
           entityManager.merge(sms);
       }
    }
}
