package br.ueg.loja.repository;

import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoComputadorRepository extends CrudRepository<TipoComputador, Long> {
    @Query(value = "select count(t) from TipoComputador t")
    long count();
}
