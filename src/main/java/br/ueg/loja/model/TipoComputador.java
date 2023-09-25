package br.ueg.loja.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_TIPO_COMPUTADOR"
)
public class TipoComputador {

    @SequenceGenerator(
            name="a_gerador_sequence",
            sequenceName = "venda_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;

    public TipoComputador(String nome){
        this.nome = nome;
    }

    public TipoComputador() {

    }
}
