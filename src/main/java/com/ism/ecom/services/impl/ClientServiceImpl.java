package com.ism.ecom.services.impl;

import com.ism.ecom.data.entities.Client;
import com.ism.ecom.data.repositories.ClientRepository;
import com.ism.ecom.exceptions.EntityNotFoundException;
import com.ism.ecom.services.ClientService;
import com.ism.ecom.api.controllers.dto.request.ClientCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Page<Client> getAllClientWithPaginateAndFilter(String keyword, Pageable page) {
        return clientRepository.findAllByTelephoneContainsAndActiveTrue(keyword,page);
    }

    @Override
    public void addClient(ClientCreateRequestDto dto) {
        Client entity = dto.toEntity();
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setActive(true);
        clientRepository.save(entity);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("article n'existe pas"));
    }

    @Override
    public Client getClientByTelephone(String telephone) {
        return clientRepository.findClientByTelephoneAndActiveTrue(telephone);
    }
}
