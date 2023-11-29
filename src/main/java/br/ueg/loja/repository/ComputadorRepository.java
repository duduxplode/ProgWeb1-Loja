package br.ueg.loja.repository;

import br.ueg.loja.model.Computador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Long>, JpaSpecificationExecutor<Computador> {
    @Query(value = "select count(c) from Computador c inner join TipoComputador t on t.id = c.fkTipoComputador.id where t.id=:paramTipo")
    Integer count(Long paramTipo);
}
