package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.TipoComputadorRepository;
import br.ueg.loja.repository.VendaRepository;
import br.ueg.loja.service.ComputadorService;
import br.ueg.loja.storage.StorageService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComputadorServiceImpl extends BaseCrudService<Computador, Long, ComputadorRepository> implements ComputadorService {

    @Autowired
    private TipoComputadorRepository tipoComputadorRepository;

    @Override
    protected void prepararParaIncluir(Computador entidade) {
        tratarTipo(entidade);
    }

    private void tratarTipo(Computador computador) {
        if(Objects.isNull(computador) ||
                Objects.isNull(computador.getFkTipoComputador()) ||
                Objects.isNull(computador.getFkTipoComputador().getId())
        ) return;
        Optional<TipoComputador> tipoOptional = tipoComputadorRepository.findById(computador.getFkTipoComputador().getId());
        tipoOptional.ifPresent(tipo -> computador.setFkTipoComputador(tipo));
    }

    @Override
    protected void validarDados(Computador computador) {
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

    @Override
    protected void validarCamposObrigatorios(Computador computador) {
        List<String> camposVazios = new ArrayList<>();

        if (Objects.isNull(computador)) camposVazios.add("Nenhum dado informado");
        if (Objects.isNull(computador.getDescricao()) || computador.getDescricao().isEmpty()) camposVazios.add("Descrição não informada");
        if (Objects.isNull(computador.getFkTipoComputador())) camposVazios.add("Tipo não informado");
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

    @Override
    public Computador alterar(Computador computador, Long id) {
        this.tratarTipo(computador);
        return super.alterar(computador, id);
    }
}
