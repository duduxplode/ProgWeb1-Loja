package br.ueg.loja.repository;

import br.ueg.loja.model.Computador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputadorRepository extends JpaRepository<Computador, Long> {
    @Query(value = "select count(c) from Computador c where c.tipo=:paramTipo")
    Integer countTipo(String paramTipo);
}
