package com.projeto.barbearia.services;

import com.projeto.barbearia.dto.AdministradorDTO;
import com.projeto.barbearia.dto.ClienteDTO;
import com.projeto.barbearia.entities.Administrador;
import com.projeto.barbearia.entities.Cliente;
import com.projeto.barbearia.repositories.ClienteRepository;
import com.projeto.barbearia.services.exception.DatabaseException;
import com.projeto.barbearia.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> dto = new ArrayList<>();
        for (Cliente c: clientes){
            dto.add(new ClienteDTO(c));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Optional<Cliente> dto = clienteRepository.findById(id);
        Cliente entity = dto.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClienteDTO((entity));
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente entity = new Cliente();
        copyEntitydto(entity, dto);
        entity = clienteRepository.save(entity);
        return new ClienteDTO(entity);
    }
    
    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto){
        try {
            Cliente entity = clienteRepository.getReferenceById(id);
            copyEntitydto(entity, dto);
            entity = clienteRepository.save(entity);
            return new ClienteDTO(entity);
        }   catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontro");
        }
    }
    @Transactional
    public void delete(Long id){
        try{
            clienteRepository.deleteById(id);
        }   catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }   catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade");
        }
    }

    public void copyEntitydto(Cliente entity, ClienteDTO dto){
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setSexo(dto.getSexo());
    }
}
