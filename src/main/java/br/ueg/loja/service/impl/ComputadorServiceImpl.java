package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.VendaRepository;
import br.ueg.loja.service.ComputadorService;
import br.ueg.prog.webi.api.exception.BusinessException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ComputadorServiceImpl implements ComputadorService {
    @Autowired
    private ComputadorRepository computadorRepository;
    @Autowired
    private VendaRepository vendaRepository;
    @Override
    public Computador incluir(Computador computador) {
        this.validarCamposObrigatorios(computador);
        this.validarDados(computador);
        Computador computadorIncluido = this.gravarDados(computador);
        return computadorIncluido;
    }

    private void validarDados(Computador computador) {
        List<String> erros = new ArrayList<>();

        if (computador.getTamanhoRam() < 0) erros.add("Tamanho de RAM incorreto");
        if (computador.getTamanhoHd() < 0) erros.add("Tamanho de HD incorreto");
        if (computador.getQuantidade() < 1) erros.add("Quantidade incorreta");

        if(!erros.isEmpty()){
            throw  new IllegalArgumentException("Erro ao validar dados do computador: "+
                    String.join(",", erros)
            );
        }
    }

    private void validarCamposObrigatorios(Computador computador) {
        List<String> camposVazios = new ArrayList<>();

        if (Objects.isNull(computador)) camposVazios.add("Nenhum dado informado");
        if (Objects.isNull(computador.getDescricao()) || computador.getDescricao().isEmpty()) camposVazios.add("Descrição não informada");
        if (Objects.isNull(computador.getTipo()) || computador.getTipo().isEmpty()) camposVazios.add("Tipo não informado");
        if (Objects.isNull(computador.getProcessador()) || computador.getProcessador().isEmpty()) camposVazios.add("Processador não informado");
        if (Objects.isNull(computador.getTamanhoRam())) camposVazios.add("Tamanho de RAM não informado");
        if (Objects.isNull(computador.getUnidadeRam()) || computador.getUnidadeRam().isEmpty()) camposVazios.add("Unidade de RAM não informada");
        if (Objects.isNull(computador.getTamanhoHd())) camposVazios.add("Tamanho de HD não informado");
        if (Objects.isNull(computador.getUnidadeHd()) || computador.getUnidadeHd().isEmpty()) camposVazios.add("Unidade de HD não informada");
        if (Objects.isNull(computador.getValorCompra())) camposVazios.add("Valor de compra não informado");
        if (Objects.isNull(computador.getValorVenda())) camposVazios.add("Valor de venda não informado");
        if (Objects.isNull(computador.getQuantidade())) camposVazios.add("Quantidade não informada");

        if(!camposVazios.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS,
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }

    private Computador gravarDados(Computador computador) {
        return computadorRepository.save(computador);
    }

    @Override
    public Computador alterar(Computador computador, Long id) {
        this.validarCamposObrigatorios(computador);
        this.validarDados(computador);
        Computador computadorBD = this.recuperarComputadorOuGeraErro(id);
        computador.setId(id);
        Computador save = computadorRepository.save(computador);
        return save;
    }

    @Override
    public Computador excluir(Long id) {
        Computador computadorExcluir = this.recuperarComputadorOuGeraErro(id);
        if (vendaRepository.count(computadorExcluir.getId()) == 0)
            this.computadorRepository.delete(computadorExcluir);
        else throw new BusinessException(SistemaMessageCode.ERRO_EXISTE_VENDA);
        return computadorExcluir;
    }

    @Override
    public Computador obterPeloId(Long id) {
        return this.recuperarComputadorOuGeraErro(id);
    }

    @Override
    public List<Computador> listarTodos() {
        return computadorRepository.findAll();
    }

    @Override
    public List<Computador> localizar(Computador computador) {
        return null;
    }

    private Computador recuperarComputadorOuGeraErro(Long id) {
        Computador computadorBD = computadorRepository
                .findById(id)
                .orElseThrow(
                        () -> new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO)
                );
        return computadorBD;
    }
}
