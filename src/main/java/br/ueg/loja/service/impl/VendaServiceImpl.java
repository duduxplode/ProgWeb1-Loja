package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.ItemVenda;
import br.ueg.loja.model.Venda;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.VendaRepository;
import br.ueg.loja.service.VendaService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaServiceImpl extends BaseCrudService<Venda, Long, VendaRepository> implements VendaService {
    @Autowired
    protected VendaRepository vendaRepository;
    @Autowired
    protected  ComputadorRepository computadorRepository;

    @Override
    protected void prepararParaIncluir(Venda venda) {
        this.validarCamposObrigatorios(venda);
        this.tratarVenda(venda);
        this.validarDados(venda);
        this.atualizarQuantidades(venda);
    }

    private void atualizarQuantidades(Venda venda) {
        for (ItemVenda item : venda.getListItensVenda()) {
            item.getFkComputador().setQuantidade(item.getFkComputador().getQuantidade() - item.getQuantidade());
        }
    }

    private void tratarVenda(Venda venda) {
        if(Objects.isNull(venda) ||
                Objects.isNull(venda.getListItensVenda()) ||
                venda.getListItensVenda().isEmpty()
        ) return;
        for (ItemVenda item : venda.getListItensVenda()) {
            Optional<Computador> tipoOptional = computadorRepository.findById(item.getFkComputador().getId());
            tipoOptional.ifPresent(computador -> item.setFkComputador(computador));
        }
    }

    @Override
    protected void validarDados(Venda venda) {
        List<String> erros = new ArrayList<>();

        for (ItemVenda item : venda.getListItensVenda()) {
            if (item.getQuantidade() > item.getFkComputador().getQuantidade())
                erros.add("Quantidade insuficiente para o computador "+ item.getFkComputador().getDescricao());
        }

        if(!erros.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO,"Erro ao validar dados da venda: "+
                    String.join(",", erros)
            );
        }
    }

    @Override
    protected void validarCamposObrigatorios(Venda venda) {
        List<String> camposVazios = new ArrayList<>();
        if(!camposVazios.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS,
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }

    public Integer contarVendas(Long idComputador) {
        return vendaRepository.count(idComputador);
    }
}
