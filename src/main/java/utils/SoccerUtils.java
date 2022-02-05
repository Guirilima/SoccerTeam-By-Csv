package utils;

import dtos.ClassificacaoDTO;
import dtos.TimeDTO;
import enums.ClubFields;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SoccerUtils {

    public static Map<String, String> convertToMap(String[] splitString) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < splitString.length; i++) {
            map.put(ClubFields.findByIndex(i), splitString[i]);
        }
        return map;
    }

    public static Path getPath(String nameFile) {
        return Paths.get("src/main/resources/" + nameFile + ".csv");
    }

    public static List<String> convertToListString(List<TimeDTO> dtos) {
        return dtos.stream().map(TimeDTO::toString).collect(Collectors.toList());
    }

    public static ClassificacaoDTO getClassificacao(List<TimeDTO> dtos, String nameTime) {
        ClassificacaoDTO classificacaoDTO = new ClassificacaoDTO();
        classificacaoDTO.setNameClub(nameTime);
        dtos.stream().parallel().forEach(dto -> {
            if(Integer.parseInt(dto.getHomeScoreboard()) > Integer.parseInt(dto.getVisitorsScoreboard())) {
                classificacaoDTO.setWins(classificacaoDTO.getWins() + 1);
                classificacaoDTO.setPoints(classificacaoDTO.getPoints() + 3);
            } else if (Integer.parseInt(dto.getHomeScoreboard()) < Integer.parseInt(dto.getVisitorsScoreboard())) {
                classificacaoDTO.setDefeats(classificacaoDTO.getDefeats() + 1);
            } else {
                classificacaoDTO.setDraws(classificacaoDTO.getDraws() + 1);
                classificacaoDTO.setPoints(classificacaoDTO.getPoints() + 1);
            }
        });
        return classificacaoDTO;
    }
}
