package com.projeto.barbearia.controlles;

import com.projeto.barbearia.dto.FuncionarioDTO;
import com.projeto.barbearia.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
        List<FuncionarioDTO> dto = funcionarioService.findAll();
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping(value = "{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id){
        FuncionarioDTO dto = funcionarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> insert(@RequestBody FuncionarioDTO dto){
        dto = funcionarioService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @RequestBody FuncionarioDTO dto){
        dto = funcionarioService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
