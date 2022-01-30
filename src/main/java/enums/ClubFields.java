package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ClubFields {

    MANDANTE("mandante", 0),
    VISITANTE("visitante", 1),
    PLACAR_MANDANTE("placar_mandante", 2),
    PLACAR_VISITANTE("placar_visitante", 3),
    DATAHORA("dataHora", 4);

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
