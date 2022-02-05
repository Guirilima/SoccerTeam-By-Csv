package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {

    private String home;

    private String visitor;

    private String homeScoreboard;

    private String visitorsScoreboard;

    private LocalDate day;

    @Override
    public String toString() {
        return home + ';' + visitor + ';' + homeScoreboard + ';' + visitorsScoreboard + ';' + day;
    }

    /* Method to populate the day field */
    public void setDay(String dataHora) {
        try {
            this.day = LocalDate.parse(this.getDate(dataHora), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }catch (Exception e) {
            throw new IllegalArgumentException("Data inserida incorretamente. " +
                    " Visitante {" + visitor + "} " +
                    " Data/Hora inserida {" + dataHora + "} ");
        }
    }

    /* Method to populate the day field */
    private String getDate(String dateStr) {
        return dateStr.substring(0,2) + "/" +
                dateStr.substring(3,5) + "/" +
                dateStr.substring(6,10);
    }

}
