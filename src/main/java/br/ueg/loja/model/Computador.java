package br.ueg.loja.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_COMPUTADOR",
        uniqueConstraints = {
                @UniqueConstraint(name= Computador.UK_DESCRICAO, columnNames = "descricao" )
        }
)
public class Computador extends BaseEntidade<Long> {
    public static final String UK_DESCRICAO = "uk_descricao";
    @SequenceGenerator(
            name="a_gerador_sequence",
            sequenceName = "computador_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    @JoinColumn(name = "fk_tipo_computador", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private TipoComputador fkTipoComputador;
    @Column(name = "tamanhoRam", nullable = false)
    private Integer tamanhoRam;
    @Column(name = "unidade_ram", length = 2, nullable = false)
    private String unidadeRam;
    @Column(name = "processador", length = 200, nullable = false)
    private String processador;
    @Column(name = "tamanho_hd", nullable = false)
    private Integer tamanhoHd;
    @Column(name = "unidade_hd", length = 2, nullable = false)
    private String unidadeHd;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;
    @Column(name = "valor_compra", nullable = false)
    private BigDecimal valorCompra;
    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVenda;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "imagem", nullable = false)
    private String imagem;
    @OneToMany(mappedBy = "fkComputador", fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Venda> listVenda;

}
