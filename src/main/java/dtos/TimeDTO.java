package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {

    private String mandante;

    private String visitante;

    private String placar_mandante;

    private String placar_visitante;

    private String dataHora;

    @Override
    public String toString() {
        return mandante + ';' + visitante + ';' + placar_mandante + ';' + placar_visitante + ';' + dataHora;
    }
}
