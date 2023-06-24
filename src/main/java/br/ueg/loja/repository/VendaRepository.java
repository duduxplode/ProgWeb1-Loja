package br.ueg.loja.repository;

import br.ueg.loja.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    @Query(value = "select count(v) from Venda v where v.fkComputador.id=:paramComputador")
    Integer count(Long paramComputador);
}
