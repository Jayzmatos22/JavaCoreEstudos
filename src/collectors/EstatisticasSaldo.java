package collectors;

import modelo.Usuario;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Média isolada vs summarizingDouble (count, sum, min, avg, max numa passada só).
 */
public class EstatisticasSaldo {

    public static void main(String[] args) {
        List<Usuario> usuarios = Usuario.amostra();

        // método separado: só a média
        double media = usuarios.stream()
                .mapToDouble(Usuario::getSaldo)
                .average()
                .orElse(0);
        System.out.println("Média: " + media);

        // min, max, avg, count, sum de uma vez
        DoubleSummaryStatistics stats = usuarios.stream()
                .collect(Collectors.summarizingDouble(Usuario::getSaldo));
        System.out.println(stats);
    }
}
