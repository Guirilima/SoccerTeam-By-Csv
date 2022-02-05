package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClubFields {

    MANDANTE("home", 0),
    VISITANTE("visitor", 1),
    PLACAR_MANDANTE("homeScoreboard", 2),
    PLACAR_VISITANTE("visitorsScoreboard", 3),
    DATAHORA("day", 4);

    private String description;
    private Integer index;

    /* Método de filtragem da linha */
    public static String findByIndex(Integer index) {
        for (ClubFields clubFields : values()) {
            if ( clubFields.getIndex().equals(index) ) return clubFields.getDescription();
        }
        throw new IllegalArgumentException();
    }
}
