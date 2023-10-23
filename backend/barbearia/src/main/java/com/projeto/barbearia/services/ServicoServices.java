package com.projeto.barbearia.services;

import com.projeto.barbearia.dto.ServicoDTO;
import com.projeto.barbearia.entities.Servico;
import com.projeto.barbearia.repositories.ServicosRepository;
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
public class ServicoServices {

    @Autowired
    private ServicosRepository servicosRepository;

    @Transactional(readOnly = true)
    public List<ServicoDTO> fildAll() {
        List<Servico> servicos = servicosRepository.findAll();
        List<ServicoDTO> servicoDTO = new ArrayList<>();
        for (Servico s: servicos){
            servicoDTO.add(new ServicoDTO(s));
        }
        return servicoDTO;
    }
    @Transactional(readOnly = true)
    public ServicoDTO findById(Long id) {
        Optional<Servico> obj = servicosRepository.findById(id);
        Servico entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ServicoDTO(entity);
    }
    @Transactional
    public ServicoDTO insert(ServicoDTO dto){
        Servico entity = new Servico();
        entity.setNome(dto.getNome());
        entity.setValor(dto.getValor());
        servicosRepository.save(entity);
        return new ServicoDTO(entity);
    }
    @Transactional
    public ServicoDTO update(Long id, ServicoDTO dto){
        try {
            Servico entity = servicosRepository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity.setValor(dto.getValor());
            entity = servicosRepository.save(entity);
            return new ServicoDTO(entity);
        }
            catch (EntityNotFoundException e){
                throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    public void delete(Long id){
        try {
            servicosRepository.deleteById(id);
        }   catch (EmptyResultDataAccessException e) {
                throw new ResourceNotFoundException("Recuso não encontrado");
        }   catch (DataIntegrityViolationException e){
                throw new DatabaseException("Falha de integridade");
        }
    }

}
