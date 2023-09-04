package com.example.barberboost.service;


import com.example.barberboost.models.AppointmentModel;
import com.example.barberboost.repositories.AppointmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentModel save(AppointmentModel appointmentModel) {
        return appointmentRepository.save(appointmentModel);
    }

    public Optional<AppointmentModel> findById(UUID id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public void delete(AppointmentModel appointmentModel){
        appointmentRepository.delete(appointmentModel);
    }

    public Page<AppointmentModel> findAllByDelete(Pageable pageable) {
        return appointmentRepository.findAllByDelete(pageable);
    }

}
