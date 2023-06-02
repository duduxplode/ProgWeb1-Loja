package br.ueg.loja;

import br.ueg.loja.model.Computador;
import br.ueg.loja.repository.ComputadorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppStartupRunner {

    public static final String NONE="none";
    public static final String CREATE_DROP="create-drop";
    public static final String CREATE = "create";
    public static final String UPDATE = "update";

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private static final Logger LOG =
            LoggerFactory.getLogger(AppStartupRunner.class);

    public AppStartupRunner(ComputadorRepository computadorRepository){
        System.out.println("Executado");
        System.out.println(computadorRepository);
        Computador c1 = new Computador();
        c1.setDescricao("Dell G3");
        c1.setTipo("Notebook");
        c1.setProcessador("Core i7 9750h");
        c1.setTamanhoRam(8);
        c1.setUnidadeRam("Gb");
        c1.setTamanhoHd(500);
        c1.setUnidadeHd("Gb");
        c1.setDataLancamento(LocalDate.parse("2020-05-04"));

        try {
            computadorRepository.save(c1);
        }catch(Exception e) {
            e.printStackTrace();
        }

        Computador c2 = new Computador();
        c2.setDescricao("Nitro 5");
        c2.setTipo("Notebook");
        c2.setProcessador("Core i5 10300h");
        c2.setTamanhoRam(8);
        c2.setUnidadeRam("Gb");
        c2.setTamanhoHd(1);
        c2.setUnidadeHd("Tb");
        c2.setDataLancamento(LocalDate.parse("2022-10-15"));

        try {
            computadorRepository.save(c2);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static void imprimirLista(ComputadorRepository computadorRepository) {
        List<Computador> lista = computadorRepository.findAll();
        lista.forEach(item -> {
            System.out.println("Computador: "+item);
        });
    }
}
