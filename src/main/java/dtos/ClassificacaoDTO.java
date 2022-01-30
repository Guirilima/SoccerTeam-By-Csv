package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ClassificacaoDTO {

    private String time;

    private Integer vitorias;

    private Integer empates;

    private Integer derrotas;

    private Integer pontos;

    public ClassificacaoDTO() {
        this.pontos = 0;
        this.empates = 0;
        this.vitorias = 0;
        this.derrotas = 0;
    }

    @Override
    public String toString() {
        return time + ';' + vitorias + ';' + empates + ';' + derrotas + ';' + pontos;
    }
}
