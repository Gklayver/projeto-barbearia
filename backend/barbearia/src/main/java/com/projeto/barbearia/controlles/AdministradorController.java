package com.projeto.barbearia.controlles;

import com.projeto.barbearia.dto.AdministradorDTO;
import com.projeto.barbearia.services.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping(value = "/login")
    public ResponseEntity<AdministradorDTO> findUsuario(
            @RequestParam(name = "login", defaultValue = "") String login ){
        AdministradorDTO dto = administradorService.findUsuario(login);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> findAll(){
        List<AdministradorDTO> dto = administradorService.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdministradorDTO> findById(@PathVariable Long id){
        AdministradorDTO dto = administradorService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AdministradorDTO> insert(@RequestBody AdministradorDTO dto){
        dto = administradorService.insert(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AdministradorDTO> update(@PathVariable Long id, @RequestBody AdministradorDTO dto){
        dto = administradorService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        administradorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
