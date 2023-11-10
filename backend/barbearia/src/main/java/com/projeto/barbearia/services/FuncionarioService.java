package com.projeto.barbearia.services;


import com.projeto.barbearia.dto.FuncionarioDTO;
import com.projeto.barbearia.entities.Funcionario;
import com.projeto.barbearia.repositories.FuncionarioRepository;
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
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public List<FuncionarioDTO> findAll(){
        List<Funcionario> funcionario = funcionarioRepository.findAll();
        List<FuncionarioDTO> dto = new ArrayList<>();
        for (Funcionario f: funcionario){
            dto.add(new FuncionarioDTO(f));
        }
        return dto;
    }

    @Transactional
    public FuncionarioDTO findById(Long id) {
        Optional<Funcionario> obj = funcionarioRepository.findById(id);
        Funcionario entity = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado!"));
        return new FuncionarioDTO(entity);
    }

    @Transactional
    public FuncionarioDTO insert(FuncionarioDTO dto){
        Funcionario entity = new Funcionario();
        copyEntityDto(entity, dto);
        entity = funcionarioRepository.save(entity);
        return new FuncionarioDTO(entity);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto){
        try {
            Funcionario entity = funcionarioRepository.getReferenceById(id);
            copyEntityDto(entity, dto);
            entity = funcionarioRepository.save(entity);
            return new FuncionarioDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Rescurso não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        try{
            funcionarioRepository.deleteById(id);
        }   catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }   catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade");
        }
    }

    public void copyEntityDto(Funcionario entity, FuncionarioDTO dto){
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setCargo(dto.getCargo());
        entity.setGenero(dto.getGenero());
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
    }


}
