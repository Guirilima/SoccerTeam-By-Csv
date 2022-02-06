package utils;

import dtos.ClassificationDTO;
import dtos.ClubDTO;
import enums.ClubFields;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SoccerUtils {

    public static final int WIN_POINTS = 3;
    public static final int DRAW_POINTS = 1;
    public static final String RESOURCES_PATH = "src/main/resources/";
    public static final String CSV_TYPE = ".csv";
    public static final String TEAM_HEADER= "|    Time - Mandante   |    Time - Visitante  |     Placar    |     Data   |";
    public static final String CLASSIFICATION_HEADER= "|         Time         |  V |  E | D  = Pts|";

    public static final String OPEN_LINE = "| ";
    public static final String INTER_LINE = " | ";
    public static final String CLOSE_LINE = " |";
    public static final String LINE = "\n----------------------------------------------------------------------------";

    /* Method for converting string array to map */
    public static Map<String, String> convertToMap(String[] splitString) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < splitString.length; i++) {
            map.put(ClubFields.findByIndex(i), splitString[i]);
        }
        return map;
    }

    /* Method for universal use of file path */
    public static Path getPath(String nameFile) {
        return Paths.get(RESOURCES_PATH + nameFile + CSV_TYPE);
    }

    /* Method used to convert entitlement to string */
    public static <G> List<String> convertToListString(Collection<G> dtos, String header) {
        List<String> collect = dtos.stream().map(G::toString).collect(Collectors.toList());
        collect.add(0,header);
        return collect;
    }

    /* Method used to convert entitlement to string */
    public static ClassificationDTO getClassification(List<ClubDTO> dtos, String nameTime) {
        ClassificationDTO classificationDTO = new ClassificationDTO();
        classificationDTO.setNameClub(nameTime);
        dtos.forEach(dto -> {
            if(Integer.parseInt(dto.getHomeScoreboard()) > Integer.parseInt(dto.getVisitorsScoreboard())) {
                classificationDTO.setWins(classificationDTO.getWins() + DRAW_POINTS);
                classificationDTO.setPoints(classificationDTO.getPoints() + WIN_POINTS);
            } else if (Integer.parseInt(dto.getHomeScoreboard()) < Integer.parseInt(dto.getVisitorsScoreboard())) {
                classificationDTO.setDefeats(classificationDTO.getDefeats() + DRAW_POINTS);
            } else {
                classificationDTO.setDraws(classificationDTO.getDraws() + DRAW_POINTS);
                classificationDTO.setPoints(classificationDTO.getPoints() + DRAW_POINTS);
            }
        });
        return classificationDTO;
    }
}
