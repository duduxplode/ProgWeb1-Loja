package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.Venda;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.VendaRepository;
import br.ueg.loja.service.VendaService;
import br.ueg.prog.webi.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VendaServiceImpl implements VendaService {
    @Autowired
    protected VendaRepository vendaRepository;
    @Autowired
    private ComputadorRepository computadorRepository;
    @Autowired
    private  UserProviderService userProviderService;
    @Override
    public Venda incluir(Venda venda) {
        this.validarCamposObrigatorios(venda);
        this.validarDados(venda);
        venda.getFkComputador().setQuantidade(venda.getFkComputador().getQuantidade() - venda.getQuantidade());
        Venda vendaIncluida = this.gravarDados(venda);
        if (Objects.nonNull(vendaIncluida)) computadorRepository.save(vendaIncluida.getFkComputador());
        return vendaIncluida;
    }

    private void validarDados(Venda venda) {
        List<String> erros = new ArrayList<>();

        if (venda.getQuantidade() > venda.getFkComputador().getQuantidade()) erros.add("Quantidade insuficiente para esta compra");
//        if (computador.getTamanhoRam() < 0) erros.add("Tamanho de RAM incorreto");
//        if (computador.getTamanhoHd() < 0) erros.add("Tamanho de HD incorreto");
//        if (computador.getQuantidade() < 1) erros.add("Quantidade incorreta");

        if(!erros.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO,"Erro ao validar dados da venda: "+
                    String.join(",", erros)
            );
        }
    }

    private void validarCamposObrigatorios(Venda venda) {
        List<String> camposVazios = new ArrayList<>();

//        if (Objects.isNull(computador)) camposVazios.add("Nenhum dado informado");
//        if (Objects.isNull(computador.getDescricao()) || computador.getDescricao().isEmpty()) camposVazios.add("Descrição não informada");
//        if (Objects.isNull(computador.getTipo()) || computador.getTipo().isEmpty()) camposVazios.add("Tipo não informado");
//        if (Objects.isNull(computador.getProcessador()) || computador.getProcessador().isEmpty()) camposVazios.add("Processador não informado");
//        if (Objects.isNull(computador.getTamanhoRam())) camposVazios.add("Tamanho de RAM não informado");
//        if (Objects.isNull(computador.getUnidadeRam()) || computador.getUnidadeRam().isEmpty()) camposVazios.add("Unidade de RAM não informada");
//        if (Objects.isNull(computador.getTamanhoHd())) camposVazios.add("Tamanho de HD não informado");
//        if (Objects.isNull(computador.getUnidadeHd()) || computador.getUnidadeHd().isEmpty()) camposVazios.add("Unidade de HD não informada");
//        if (Objects.isNull(computador.getValorCompra())) camposVazios.add("Valor de compra não informado");
//        if (Objects.isNull(computador.getValorVenda())) camposVazios.add("Valor de venda não informado");
//        if (Objects.isNull(computador.getQuantidade())) camposVazios.add("Quantidade não informada");

        if(!camposVazios.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS,
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }

    private Venda gravarDados(Venda venda) {
        return vendaRepository.save(venda);
    }

    @Override
    public Venda alterar(Venda venda, Long id) {
        this.validarCamposObrigatorios(venda);
        this.validarDados(venda);
        Venda vendaBD = this.recuperarVendaOuGeraErro(id);
        venda.setId(id);
        Venda save = vendaRepository.save(venda);
        return save;
    }

    @Override
    public Venda excluir(Long id) {
        Venda vendaExcluir = this.recuperarVendaOuGeraErro(id);
        this.vendaRepository.delete(vendaExcluir);
        return vendaExcluir;
    }

    @Override
    public Venda obterPeloId(Long id) {
        return this.recuperarVendaOuGeraErro(id);
    }

    @Override
    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    @Override
    public List<Venda> localizar(Venda venda) {
        return null;
    }

    private Venda recuperarVendaOuGeraErro(Long id) {
        Venda vendaBD = vendaRepository
                .findById(id)
                .orElseThrow(
                        () -> new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO)
                );
        return vendaBD;
    }

    public Integer contarVendas(Long idComputador) {
        return vendaRepository.count(idComputador);
    }
}
