package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import static utils.SoccerUtils.*;
import static utils.SoccerUtils.LINE;

@Setter
@Getter
@AllArgsConstructor
public class ClassificationDTO {

    private String nameClub;

    private Integer wins;

    private Integer draws;

    private Integer defeats;

    private Integer points;

    public ClassificationDTO() {
        this.points = 0;
        this.draws = 0;
        this.wins = 0;
        this.defeats = 0;
    }

    @Override
    public String toString() {
//        return nameClub + ';' + wins + ';' + draws + ';' + defeats + ';' + points;
        return OPEN_LINE +
                StringUtils.rightPad(nameClub, 20, "") + INTER_LINE +
                StringUtils.rightPad(String.valueOf(wins), 2, "") + INTER_LINE +
                StringUtils.rightPad(String.valueOf(draws), 2, "") + INTER_LINE +
                StringUtils.rightPad(String.valueOf(defeats), 2, "") + " = " +
                points + CLOSE_LINE +
                "\n--------------------------------------------";
    }
}
