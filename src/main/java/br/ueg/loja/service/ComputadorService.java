package br.ueg.loja.service;

import br.ueg.loja.model.Computador;

import java.util.List;

public interface ComputadorService {
    public Computador incluir(Computador computador);
    public Computador alterar(Computador computador);
    public Computador excluir(Long id);
    public Computador obterComputadorPeloId(Long id);
    public List<Computador> listarTodos();
    public List<Computador>localizar(Computador computador);
}
