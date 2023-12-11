package br.ueg.loja.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_ITEM_VENDA"
)
public class ItemVenda extends BaseEntidade<Long> {
    @SequenceGenerator(
            name="a_gerador_sequence",
            sequenceName = "item_venda_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )
    @Id
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "fk_venda", referencedColumnName = "id")
    @ManyToOne
    private Venda fkVenda;
    @JoinColumn(name = "fk_computador", referencedColumnName = "id")
    @ManyToOne
    private Computador fkComputador;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;
    @Column(name = "valor_total", length = 2, nullable = false)
    private BigDecimal valorTotal ;
}
