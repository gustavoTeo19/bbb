package com.example.barberboost.service;


import com.example.barberboost.models.ServiceModel;
import com.example.barberboost.repositories.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceService {
    final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public ServiceModel save(ServiceModel serviceModel) {
        return serviceRepository.save(serviceModel);
    }

    public Page<ServiceModel> findAllByDelete(Pageable pageable) {
        return serviceRepository.findAllByDelete(pageable);
    }

    public Optional<ServiceModel> findById(UUID id) {
        return serviceRepository.findById(id);
    }
}
