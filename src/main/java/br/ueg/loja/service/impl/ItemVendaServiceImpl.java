package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.ItemVenda;
import br.ueg.loja.model.Venda;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.ItemVendaRepository;
import br.ueg.loja.repository.VendaRepository;
import br.ueg.loja.service.ItemVendaService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemVendaServiceImpl extends BaseCrudService<ItemVenda, Long, ItemVendaRepository> implements ItemVendaService {
    @Autowired
    protected VendaRepository vendaRepository;
    @Autowired
    protected  ComputadorRepository computadorRepository;

    @Override
    protected void prepararParaIncluir(ItemVenda itemVenda) {
        this.validarCamposObrigatorios(itemVenda);
        this.validarDados(itemVenda);
        this.atualizarQuantidades(itemVenda);
    }

    private void atualizarQuantidades(ItemVenda itemVenda) {
        if(Objects.isNull(itemVenda) ||
                Objects.isNull(itemVenda.getFkComputador())) {
            itemVenda.getFkComputador().setQuantidade(itemVenda.getFkComputador().getQuantidade() - itemVenda.getQuantidade());
        }
    }

    private void tratarItem(ItemVenda itemVenda) {
        if(Objects.isNull(itemVenda) ||
                Objects.isNull(itemVenda.getFkComputador()) ||
                Objects.isNull(itemVenda.getFkVenda())
        ) return;
        Optional<Computador> tipoOptional = computadorRepository.findById(itemVenda.getFkComputador().getId());
        tipoOptional.ifPresent(computador -> itemVenda.setFkComputador(computador));
        Optional<Venda> tipoOptionalVenda = vendaRepository.findById(itemVenda.getFkVenda().getId());
        tipoOptionalVenda.ifPresent(venda -> itemVenda.setFkVenda(venda));
    }

    @Override
    protected void validarDados(ItemVenda itemVenda) {
        List<String> erros = new ArrayList<>();
        this.tratarItem(itemVenda);
        if (itemVenda.getQuantidade() > itemVenda.getFkComputador().getQuantidade())
                erros.add("Quantidade insuficiente para o computador "+ itemVenda.getFkComputador().getDescricao());

        if(!erros.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO,"Erro ao validar dados da venda: "+
                    String.join(",", erros)
            );
        }
    }

    @Override
    protected void validarCamposObrigatorios(ItemVenda itemVenda) {
        List<String> camposVazios = new ArrayList<>();
        if(!camposVazios.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS,
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }

    public Integer contarItens(Long idComputador) {
        return vendaRepository.count(idComputador);
    }
}
