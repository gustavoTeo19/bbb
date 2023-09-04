package com.example.barberboost.repositories;

import com.example.barberboost.models.LoyaltyPlanModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoyaltyPlanRepository extends JpaRepository<LoyaltyPlanModel, UUID> {

    @Query("select e from #{#entityName} e where e.isDelete = false")
    Page<LoyaltyPlanModel> findAllByDelete(Pageable pageable);
}
