package br.ueg.loja.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_VENDA"
)
public class Venda {

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
    @JoinColumn(name = "fk_computador", referencedColumnName = "id")
    @ManyToOne
    private Computador fkComputador;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;
    @Column(name = "valor_total", length = 2, nullable = false)
    private BigDecimal valorTotal ;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;

}
