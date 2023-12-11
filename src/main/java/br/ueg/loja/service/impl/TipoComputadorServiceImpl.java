package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.TipoComputadorRepository;
import br.ueg.loja.service.TipoComputadorService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TipoComputadorServiceImpl extends BaseCrudService<TipoComputador, Long, TipoComputadorRepository> implements TipoComputadorService {

    @Override
    protected void prepararParaIncluir(TipoComputador entidade) {
        validarDados(entidade);
        validarCamposObrigatorios(entidade);
    }

    @Override
    protected void validarDados(TipoComputador tipoComputador) {
        List<String> erros = new ArrayList<>();

        if (tipoComputador.getNome().length() < 2) erros.add("Tamanho de nome incorreto");

        if(!erros.isEmpty()){
            throw  new IllegalArgumentException("Erro ao validar dados do tipo de computador: "+
                    String.join(",", erros)
            );
        }
    }

    @Override
    protected void validarCamposObrigatorios(TipoComputador tipoComputador) {
        List<String> camposVazios = new ArrayList<>();

        if (Objects.isNull(tipoComputador)) camposVazios.add("Nenhum dado informado");
        if (Objects.isNull(tipoComputador.getNome()) || tipoComputador.getNome().isEmpty()) camposVazios.add("Nome não informado");

        if(!camposVazios.isEmpty()){
            throw  new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS,
                    "Campos Obrigatórios não preenchidos ("+
                            String.join(",",camposVazios)+")"
            );
        }
    }
}
