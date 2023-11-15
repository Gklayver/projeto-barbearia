package com.projeto.barbearia.services;

import com.projeto.barbearia.dto.AdministradorDTO;
import com.projeto.barbearia.dto.AgendamentoDTO;
import com.projeto.barbearia.entities.Administrador;
import com.projeto.barbearia.entities.Agendamento;
import com.projeto.barbearia.entities.Servico;
import com.projeto.barbearia.repositories.AgendamentoRepository;
import com.projeto.barbearia.repositories.ServicosRepository;
import com.projeto.barbearia.services.exception.DatabaseException;
import com.projeto.barbearia.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ServicosRepository servicosRepository;
    @Transactional(readOnly = true)
    public List<AgendamentoDTO> findAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        List<AgendamentoDTO> dto = new ArrayList<>();
        for (Agendamento a : agendamentos) {
            dto.add(new AgendamentoDTO(a));
        }
        return dto;
    }


    @Transactional
    public AgendamentoDTO insert(AgendamentoDTO dto) {
        Agendamento entity = new Agendamento();
        copyEntitydto(entity, dto);
        entity = agendamentoRepository.save(entity);
        return new AgendamentoDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<AgendamentoDTO> findByData(LocalDate data){
        List<AgendamentoDTO> dto = agendamentoRepository.findByData(data).stream()
                .map(agendamento -> new AgendamentoDTO(agendamento)).toList();
        return dto;
    }
    @Transactional
    public AgendamentoDTO update(Long id, AgendamentoDTO dto) {
        try {
            Agendamento entity = agendamentoRepository.getReferenceById(id);
            copyEntitydto(entity, dto);
            entity = agendamentoRepository.save(entity);
            return new AgendamentoDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Rescurso não encontrado");
        }
    }
    @Transactional
    public void delete(Long id){
        try{
            agendamentoRepository.deleteById(id);
        }   catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }   catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade");
        }
    }

    @Transactional
    public AgendamentoDTO findById(Long id) {
        Optional<Agendamento> obj = agendamentoRepository.findById(id);
        Agendamento entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new AgendamentoDTO(entity);
    }

    public void copyEntitydto(Agendamento entity, AgendamentoDTO dto) {
        List<Servico> servicos = new ArrayList<>();

        entity.setData(dto.getData());
        entity.setHora(dto.getHora());
        entity.setIdCliente(dto.getIdCliente());
        entity.setIdFuncionario(dto.getIdFuncionario());
        for (Servico s : dto.getServicos()) {
            servicos.add(servicosRepository.findById(s.getId()).get());
        }
        entity.setServicos(servicos);
    }
}