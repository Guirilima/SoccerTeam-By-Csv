package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ClassificacaoDTO {

    private String nameClub;

    private Integer wins;

    private Integer draws;

    private Integer defeats;

    private Integer points;

    public ClassificacaoDTO() {
        this.points = 0;
        this.draws = 0;
        this.wins = 0;
        this.defeats = 0;
    }

    @Override
    public String toString() {
        return nameClub + ';' + wins + ';' + draws + ';' + defeats + ';' + points;
    }
}
