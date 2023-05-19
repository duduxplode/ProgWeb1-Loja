package br.ueg.loja.service;

import br.ueg.api.service.CrudService;
import br.ueg.loja.model.Computador;

import java.util.List;

public interface ComputadorService extends CrudService<Computador, Long> {
    List<Computador> localizar(Computador computador);
}
