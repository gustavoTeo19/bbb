package com.example.barberboost.service;


import com.example.barberboost.models.ClientModel;
import com.example.barberboost.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public Page<ClientModel> findAllByDelete(Pageable pageable) {
        return clientRepository.findAllByDelete(pageable);
    }

    public Optional<ClientModel> findByid(UUID id) {
        return clientRepository.findById(id);
    }

    public Optional<ClientModel> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public boolean existByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
