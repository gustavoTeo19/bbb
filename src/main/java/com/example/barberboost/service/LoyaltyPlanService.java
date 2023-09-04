package com.example.barberboost.service;


import com.example.barberboost.models.LoyaltyPlanModel;
import com.example.barberboost.repositories.LoyaltyPlanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LoyaltyPlanService {
    final LoyaltyPlanRepository loyaltyPlanRepository;

    public LoyaltyPlanService(LoyaltyPlanRepository loyaltyPlanRepository) {
        this.loyaltyPlanRepository = loyaltyPlanRepository;
    }

    public Page<LoyaltyPlanModel> findAllByDelete(Pageable pageable) {
        return loyaltyPlanRepository.findAllByDelete(pageable);
    }

    public Optional<LoyaltyPlanModel> findByid(UUID id) {
        return loyaltyPlanRepository.findById(id);
    }

    public LoyaltyPlanModel save(LoyaltyPlanModel loyaltyPlanModel) {
        return loyaltyPlanRepository.save(loyaltyPlanModel);
    }


}
