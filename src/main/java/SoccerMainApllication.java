import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.ClassificacaoDTO;
import dtos.TimeDTO;
import org.apache.commons.lang3.StringUtils;
import utils.SoccerUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SoccerMainApllication {

    public static void main(String[] args) throws IOException {

        List<ClassificacaoDTO> classificacao = new ArrayList<>();

        Files.lines(SoccerUtils.getPath("brasileirao"), StandardCharsets.UTF_8)
                .filter(StringUtils::isNotBlank) /*Removendo Linhas em brancas*/
                .distinct() /* Removendo linhas repetidas */
                .map(field -> /* Formatando linha para TimedTO */
                        new ObjectMapper().convertValue(SoccerUtils.convertToMap(field.split(";")), TimeDTO.class)
                ).sorted(Comparator.comparing(TimeDTO::getDay, Comparator.reverseOrder()))/* Ordenando baseado no DataHora */

                //Subdivida a estrutura de dados por time (mandante)
                .collect(
                        Collectors.groupingBy( TimeDTO::getHome ))
                .forEach((key,dtos) -> {
                    try {
                        /* Imprimindo o histórico de cada time */
                        Files.write(SoccerUtils.getPath(key), SoccerUtils.convertToListString(dtos), StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    classificacao.add(SoccerUtils.getClassificacao(dtos, key));
                });

        var listClassificacao = classificacao.stream()
                .sorted(Comparator.comparing(ClassificacaoDTO::getPoints, Comparator.reverseOrder()).thenComparing(ClassificacaoDTO::getWins,Comparator.reverseOrder()))
                .map(time -> time.toString()).collect(Collectors.toList());

        Files.write(SoccerUtils.getPath("Classificacao"), listClassificacao, StandardCharsets.UTF_8);

    }


    /**
     * Segue a descrição do projeto:
     *
     * Dado um arquivo(.csv), no formato abaixo, contendo o resultado de todos os jogos de futebol de um determinado campeonato:
     *
     * time_1(mandante);time2(visitante);placar_time_1;placar_time_2;data_hora
     * Sao Paulo;Corinthians;5;0;2022-01-01 21:00:00
     * Sao Paulo;Palmeiras;3;2;2022-02-01 21:00:00
     * Sao Paulo;Santos;3;1;2022-02-03 21:00:00
     *
     * Santos;Sao Caetano;1;1;2022-02-03 21:00:00
     * Santos;Sao Paulo;1;2;2022-02-04 21:00:00
     *
     * Leia o arquivo em uma estrutura de dados, remova os registros duplicados(se houver), ordene por data/hora, time_1 e time_2.
     * todo -> 1. Extrair lista do arquivo.
     * todo -> 2. Remove registros duplicados.
     * todo -> 3. Ordenar por data/hora / time_1 e time_2
     *
     * Subdivida a estrutura de dados por time (mandante) mantendo as ordens anteriores e gere um arquivo por time contendo o
     * historico dos jogos ordenados por data. Gere um arquivo contendo a tabela de classificação final dos times, ordenado do
     * que tiver mais pontos para o que tiver menos pontos (lembrando que: vitoria = 3pts, empate = 1pt, derrota = 0pt).
     * todo -> 1. Extrair da lista, sub-listas dividido por mandante (Continuando a ordem anterior).
     * todo -> 2. Gerar um arquivo (Ainda ordernado por data).
     *
     * Identificar a quantidade de vitorias, empates e derrotas de cada time. O arquivo com a tabela de classificação final
     * deverá ser gerado no formato .csv, utilizando o separador ";", os demais poderão ser .txt.
     * todo -> 1. Identificar resultado dos jogos e atribuir a pontual por time.
     * todo -> 2. Gerar novo arquivo com a classificação final dos times no formato .csv, utilizando ; como separador.
     *
     * Modelo arquivo por time:
     * 01/01/22, 21h00: Sao Paulo 5 x 0 Corinthians
     * 01/02/22, 21h00: Sao Paulo 3 x 2 Palmeiras
     * ...
     *
     * Modelo Arquivo resultado final
     * Time;Vitorias;Empates;Derrotas;Pontos
     * Sao Paulo;42;10;0;
     * Palmeiras;35;5;2;
     * Santos;30;5;7;
     * Corinthians;12;20;10;
     * ...
     *
     * Requisitos obrigatórios:
     * - o arquivo contendo a relação de jogos será fornecido pelo professor (registros desordenados e/ou duplicados)
     * - aplicar princípios de OO
     * - utilizar uma ferramenta de build/gerenciador de pacotes: Maven ou Gradle
     * - utilizar ao menos 2 dependências
     * - utilizar ao menos 2 estruturas de dados diferentes
     *
     * Itens avaliados:
     * - ferramenta de build/gerenciador de pacotes
     * - uso de dependencias
     * - estrutura de dados
     * - ordenação
     * - manipulação de arquivos (leitura e escrita)
     * - fundamentos de OO
     */

}
