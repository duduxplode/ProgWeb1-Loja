package br.ueg.loja.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_VENDA"
)
public class Venda extends BaseEntidade<Long> {

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
    @Column(name = "cliente", nullable = false)
    private String cliente;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "valor_total", length = 2, nullable = false)
    private BigDecimal valorTotal ;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;
    @OneToMany(mappedBy = "fkVenda", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private Set<ItemVenda> listItensVenda = new HashSet<>();

}
