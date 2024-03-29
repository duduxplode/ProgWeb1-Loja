package br.ueg.loja;

import br.ueg.loja.model.Computador;
import br.ueg.loja.model.TipoComputador;
import br.ueg.loja.repository.ComputadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class AppStartupRunner implements ApplicationRunner {

    public static final String NONE="none";
    public static final String CREATE_DROP="create-drop";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private static final Logger LOG =
            LoggerFactory.getLogger(AppStartupRunner.class);

    @Autowired
    private ComputadorRepository computadorRepository;

    public void initDados(){
        System.out.println("Executado");
        System.out.println(computadorRepository);
        Computador c1 = new Computador();
        c1.setDescricao("Dell G3");
        c1.setFkTipoComputador(new TipoComputador("Notebook"));
        c1.setProcessador("Core i7 9750h");
        c1.setTamanhoRam(8);
        c1.setUnidadeRam("Gb");
        c1.setTamanhoHd(500);
        c1.setUnidadeHd("Gb");
        c1.setDataLancamento(LocalDate.parse("2020-05-04"));
        c1.setValorCompra(BigDecimal.valueOf(3500));
        c1.setValorVenda(BigDecimal.valueOf(4500));
        c1.setQuantidade(5);
        computadorRepository.save(c1);

        Computador c2 = new Computador();
        c2.setDescricao("Nitro 5");
        c1.setFkTipoComputador(new TipoComputador("Notebook"));
        c2.setProcessador("Core i5 10300h");
        c2.setTamanhoRam(8);
        c2.setUnidadeRam("Gb");
        c2.setTamanhoHd(1);
        c2.setUnidadeHd("Tb");
        c2.setDataLancamento(LocalDate.parse("2022-10-15"));
        c1.setValorCompra(BigDecimal.valueOf(3000));
        c1.setValorVenda(BigDecimal.valueOf(4000));
        c1.setQuantidade(10);
        computadorRepository.save(c2);

        imprimirLista();
    }

    private void imprimirLista() {
        List<Computador> lista = computadorRepository.findAll();
        lista.forEach(item -> {
            System.out.println("Computador: "+item);
        });
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Start Runner");
        this.initDados();
    }
}
