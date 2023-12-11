package br.ueg.loja.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.annotation.Searchable;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "TBL_TIPO_COMPUTADOR")
@Builder
@AllArgsConstructor
@ToString
public @Getter
@Setter
@RequiredArgsConstructor
class TipoComputador extends BaseEntidade<Long> {

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
    @Searchable(label = "Número")
    private Long id;
    @Column(name = "nome", nullable = false)
    @Searchable
    private String nome;

    public TipoComputador(String nome){
        this.nome = nome;
    }

}
