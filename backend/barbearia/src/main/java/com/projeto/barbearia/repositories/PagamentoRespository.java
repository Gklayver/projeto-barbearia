package com.projeto.barbearia.repositories;

import com.projeto.barbearia.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRespository extends JpaRepository<Pagamento, Long> {
}
