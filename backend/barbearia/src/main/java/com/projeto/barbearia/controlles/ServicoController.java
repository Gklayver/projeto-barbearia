package com.projeto.barbearia.controlles;

import com.projeto.barbearia.dto.ServicoDTO;
import com.projeto.barbearia.entities.Servico;
import com.projeto.barbearia.services.ServicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {

    @Autowired
    private ServicoServices servicoServices;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> fildAll(){
        List<ServicoDTO> servicosdto = servicoServices.fildAll();
        return ResponseEntity.ok().body(servicosdto);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicoDTO> findById(@PathVariable Long id ){
        ServicoDTO dto = servicoServices.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<ServicoDTO> insert(@RequestBody ServicoDTO dto){
        dto = servicoServices.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @RequestBody ServicoDTO dto){
        dto = servicoServices.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        servicoServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
