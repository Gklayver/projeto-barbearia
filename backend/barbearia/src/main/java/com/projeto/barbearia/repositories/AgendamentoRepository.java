package com.projeto.barbearia.repositories;

import com.projeto.barbearia.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    @Query("SELECT a FROM Agendamento a WHERE a.data = :data")
    List<Agendamento> findByData(LocalDate data);
}
