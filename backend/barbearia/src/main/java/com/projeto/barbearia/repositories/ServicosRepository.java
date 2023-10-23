package com.projeto.barbearia.repositories;

import com.projeto.barbearia.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends JpaRepository<Servico, Long> {
}
