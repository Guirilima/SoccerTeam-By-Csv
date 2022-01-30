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
        classificacaoDTO.setTime(nameTime);
        dtos.stream().parallel().forEach(dto -> {
            if(Integer.parseInt(dto.getPlacar_mandante()) > Integer.parseInt(dto.getPlacar_visitante())) {
                classificacaoDTO.setVitorias(classificacaoDTO.getVitorias() + 1);
                classificacaoDTO.setPontos(classificacaoDTO.getPontos() + 3);
            } else if (Integer.parseInt(dto.getPlacar_mandante()) < Integer.parseInt(dto.getPlacar_visitante())) {
                classificacaoDTO.setDerrotas(classificacaoDTO.getDerrotas() + 1);
            } else {
                classificacaoDTO.setEmpates(classificacaoDTO.getEmpates() + 1);
                classificacaoDTO.setPontos(classificacaoDTO.getPontos() + 1);
            }
        });
        return classificacaoDTO;
    }
}
