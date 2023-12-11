package br.ueg.loja.repository;

import br.ueg.loja.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>, JpaSpecificationExecutor<ItemVenda> {
    @Query(value = "select count(i) from ItemVenda i where i.fkComputador.id=:paramComputador")
    Integer count(Long paramComputador);
}
