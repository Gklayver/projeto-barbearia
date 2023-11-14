package com.projeto.barbearia.services;

import com.projeto.barbearia.dto.AdministradorDTO;
import com.projeto.barbearia.dto.AgendamentoDTO;
import com.projeto.barbearia.entities.Administrador;
import com.projeto.barbearia.entities.Servico;
import com.projeto.barbearia.repositories.AdministradorRepository;
import com.projeto.barbearia.services.exception.DatabaseException;
import com.projeto.barbearia.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Transactional(readOnly = true)
    public List<AdministradorDTO> findAll(){
        List<Administrador> administradores = administradorRepository.findAll();
        List<AdministradorDTO> dto = new ArrayList<>();
        for (Administrador a: administradores){
            dto.add(new AdministradorDTO(a));
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public AdministradorDTO findUsuario(String login){
        Administrador entity = administradorRepository.findByLogin(login);
        if(entity == null ){
            throw new ResourceNotFoundException("Login não encontrado!");
        }
        return new AdministradorDTO(entity);
    }

    @Transactional
    public AdministradorDTO findById(Long id){
        Optional<Administrador> obj = administradorRepository.findById(id);
        Administrador entity = obj.orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado!"));
        return new AdministradorDTO(entity);
    }
    @Transactional
    public AdministradorDTO insert(AdministradorDTO dto){
        Administrador entity = new Administrador();
        copyEntitydto(entity, dto);
        entity = administradorRepository.save(entity);
        return new AdministradorDTO(entity);
    }

    @Transactional
    public AdministradorDTO update(Long id, AdministradorDTO dto){
        try {
            Administrador entity = administradorRepository.getReferenceById(id);
            copyEntitydto(entity, dto);
            entity = administradorRepository.save(entity);
            return new AdministradorDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Rescurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        try{
            administradorRepository.deleteById(id);
        }   catch (EmptyResultDataAccessException e){
                throw new ResourceNotFoundException("Recurso não encontrado");
        }   catch (DataIntegrityViolationException e){
                throw new DatabaseException("Falha de integridade");
        }
  }

    public void copyEntitydto(Administrador entity, AdministradorDTO dto){
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
    }
}
