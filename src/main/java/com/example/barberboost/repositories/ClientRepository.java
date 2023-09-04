package com.example.barberboost.repositories;

import com.example.barberboost.models.ClientModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, UUID> {

    @Query("select e from #{#entityName} e where e.isDelete = false")
    Page<ClientModel> findAllByDelete(Pageable pageable);

    Optional<ClientModel> findByEmail(String email);
    boolean existsByEmail(String email);
}
