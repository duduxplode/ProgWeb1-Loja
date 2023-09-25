package br.ueg.loja.service.impl;

import br.ueg.loja.exception.SistemaMessageCode;
import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.repository.ComputadorRepository;
import br.ueg.loja.repository.TipoComputadorRepository;
import br.ueg.loja.service.TipoComputadorService;
import br.ueg.prog.webi.api.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TipoComputadorServiceImpl implements TipoComputadorService {


    @Autowired
    private TipoComputadorRepository tipoComputadorRepository;
    @Autowired
    private ComputadorRepository computadorRepository;

    @Override
    public TipoComputador incluir(TipoComputador tipoComputador) {
        this.validarCamposObrigatorios(tipoComputador);
        this.validarDados(tipoComputador);
        TipoComputador tipoComputadorIncluido = this.gravarDados(tipoComputador);
        return tipoComputadorIncluido;
    }

    private void validarDados(TipoComputador tipoComputador) {
        List<String> erros = new ArrayList<>();

        if (tipoComputador.getNome().length() < 2) erros.add("Tamanho de nome incorreto");

        if(!erros.isEmpty()){
            throw  new IllegalArgumentException("Erro ao validar dados do tipo de computador: "+
                    String.join(",", erros)
            );
        }
    }

    private void validarCamposObrigatorios(TipoComputador tipoComputador) {
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

    private TipoComputador gravarDados(TipoComputador tipoComputador) {
        return tipoComputadorRepository.save(tipoComputador);
    }

    @Override
    public TipoComputador alterar(TipoComputador tipoComputador, Long id) {
        this.validarCamposObrigatorios(tipoComputador);
        this.validarDados(tipoComputador);
        TipoComputador computadorBD = this.recuperarTipoComputadorOuGeraErro(id);
        tipoComputador.setId(id);
        TipoComputador save = tipoComputadorRepository.save(tipoComputador);
        return save;
    }

    @Override
    public TipoComputador excluir(Long id) {
        TipoComputador tipoComputadorExcluir = this.recuperarTipoComputadorOuGeraErro(id);
        if (computadorRepository.count(tipoComputadorExcluir.getId()) == 0)
            this.tipoComputadorRepository.delete(tipoComputadorExcluir);
        else throw new BusinessException(SistemaMessageCode.ERRO_EXISTE_COMPUTADOR);
        return tipoComputadorExcluir;
    }

    @Override
    public TipoComputador obterPeloId(Long id) {
        return this.recuperarTipoComputadorOuGeraErro(id);
    }

    @Override
    public List<TipoComputador> listarTodos() {
        return (List<TipoComputador>) tipoComputadorRepository.findAll();
    }

    @Override
    public List<TipoComputador> localizar(TipoComputador tipoComputador) {
        return null;
    }

    private TipoComputador recuperarTipoComputadorOuGeraErro(Long id) {
        TipoComputador tipoComputadorBD = tipoComputadorRepository
                .findById(id)
                .orElseThrow(
                        () -> new BusinessException(SistemaMessageCode.ERRO_REGISTRO_NAO_ENCONTRADO)
                );
        return tipoComputadorBD;
    }
}
