package comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Fila de triagem hospitalar (01/09).
 * 1) Paciente[] por prioridade ↓, idade ↓, tempo de espera ↓.
 * 2) List<Paciente> com nulls, alfabética, nulls no fim (dois níveis de nullsLast).
 */
public class TriagemHospitalar {

    public static void main(String[] args) {
        Paciente[] filaArray = {
                new Paciente("Ana", 2, 25, 90),
                new Paciente("Carlos", 4, 70, 40),
                new Paciente("Elena", 4, 45, 60),
                new Paciente("Beatriz", 5, 30, 10),
                new Paciente("Daniel", 4, 70, 20)
        };

        Arrays.sort(filaArray,
                Comparator.comparing(Paciente::getPrioridade, Comparator.reverseOrder())
                        .thenComparing(Paciente::getIdade, Comparator.reverseOrder())
                        .thenComparing(Paciente::getTempoEsperaMinutos, Comparator.reverseOrder()));

        System.out.println("Array por prioridade: " + Arrays.toString(filaArray));
        // Beatriz, Carlos, Daniel, Elena, Ana

        // List.of não aceita null, por isso ArrayList
        List<Paciente> filaLista = new ArrayList<>(Arrays.asList(
                new Paciente("elena", 1, 40, 5),
                null,                                  // nível 1: o elemento é null
                new Paciente("Ana", 3, 22, 15),
                new Paciente(null, 2, 50, 30),         // nível 2: o atributo é null
                new Paciente("Carlos", 1, 60, 45)
        ));

        filaLista.sort(Comparator.nullsLast(
                Comparator.comparing(Paciente::getNome,
                        Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))));

        System.out.println("Lista alfabética: " + filaLista);
        // Ana, Carlos, elena, null (nome null), null (elemento null)
    }
}
