package com.projeto.barbearia.controlles;

import com.projeto.barbearia.dto.AdministradorDTO;
import com.projeto.barbearia.dto.AgendamentoDTO;
import com.projeto.barbearia.dto.AgendamentoDTO;
import com.projeto.barbearia.services.AdministradorService;
import com.projeto.barbearia.services.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(value = "/agendamentos")
public class AgendamentoController {
    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> findAll(){
        List<AgendamentoDTO> dto = agendamentoService.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> insert(@RequestBody AgendamentoDTO dto){
        dto = agendamentoService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @GetMapping(value = "/{data}")
    public ResponseEntity<List<AgendamentoDTO>> findByData(@PathVariable LocalDate data){
        List<AgendamentoDTO> dto = agendamentoService.findByData(data);
        return ResponseEntity.ok().body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<AgendamentoDTO> update(@PathVariable Long id, @RequestBody AgendamentoDTO dto){
        dto = agendamentoService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        agendamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(value = "#/{id}")
    public ResponseEntity<AgendamentoDTO> findById(@PathVariable Long id){
        AgendamentoDTO dto = agendamentoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

}