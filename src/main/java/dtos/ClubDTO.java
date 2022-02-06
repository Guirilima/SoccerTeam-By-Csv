package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utils.SoccerUtils.INTER_LINE;
import static utils.SoccerUtils.OPEN_LINE;
import static utils.SoccerUtils.CLOSE_LINE;
import static utils.SoccerUtils.LINE;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClubDTO {

    private String home;

    private String visitor;

    private String homeScoreboard;

    private String visitorsScoreboard;

    private LocalDate day;

    @Override
    public String toString() {
//        return home + ';' + visitor + ';' + homeScoreboard + ';' + visitorsScoreboard + ';' + day;
        return OPEN_LINE +
                StringUtils.rightPad(home, 20, "") + INTER_LINE +
                StringUtils.rightPad(visitor, 20, "") + INTER_LINE +
                StringUtils.leftPad(homeScoreboard, 5, "") + " x " +
                StringUtils.rightPad(visitorsScoreboard, 5, "") + INTER_LINE +
                day + CLOSE_LINE +
                LINE;
    }

    /* Method to populate the day field */
    public void setDay(String dataHora) {
        try {
            this.day = LocalDate.parse(this.getDate(dataHora), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }catch (Exception e) {
            throw new IllegalArgumentException("Data inserida incorretamente. " +
                    " Visitante {" + visitor + "} " +
                    " Data/Hora inserida {" + dataHora + "} ");
        }
    }

    /* Method to populate the day field */
    private String getDate(String dateStr) {
        return dateStr.substring(0,4) + "/" +
                dateStr.substring(5,7) + "/" +
                dateStr.substring(8,10);
    }

}
