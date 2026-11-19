package enums;

import modelo.BancoEnum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



/**
 * Consultas sobre o enum: values() vira stream como qualquer array.
 */
public class BancoConsultas {

    // índice por código montado uma vez só: busca O(1) em vez de varrer o enum
    public static final Map<String, BancoEnum> P_CODIGO =
            Arrays.stream(BancoEnum.values())
                    .collect(Collectors.toMap(BancoEnum::getCodigo, Function.identity()));

    public static BancoEnum porCodigo(String cod) {
        return Arrays.stream(BancoEnum.values())
                .filter(b -> b.getCodigo().equals(cod))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Não encontrado"));
    }

    public static List<String> porNome() {
        return Arrays.stream(BancoEnum.values())
                .sorted(Comparator.comparing(BancoEnum::getNome))
                .map(BancoEnum::getNome)
                .toList();
    }

    public static Map<String, List<BancoEnum>> primeiraLetra() {
        return Arrays.stream(BancoEnum.values())
                .collect(Collectors.groupingBy(b -> String.valueOf(b.getNome().charAt(0))));
    }

    public static String formatada() {
        return Arrays.stream(BancoEnum.values())
                .map(b -> b.getCodigo() + " - " + b.getNome())
                .collect(Collectors.joining("\n"));
    }


    public static List<String> listaPrimeiraLetra(){
        return Arrays.stream(BancoEnum.values())
                .map(b -> String.valueOf(b.getNome().charAt(0)))
                        .collect(Collectors.toList());
    }

    public static String primeirasLetrasJuntas(){
        return Arrays.stream(BancoEnum.values())
                .map(b -> String.valueOf(b.getNome().charAt(0)))
                .collect(Collectors.joining(""));
    }



    public static void main(String[] args) {
        System.out.println("porCodigo(341): " + porCodigo("341"));
        System.out.println("P_CODIGO.get(260): " + P_CODIGO.get("260"));
        try {
            porCodigo("999");
        } catch (IllegalArgumentException e) {
            System.out.println("porCodigo(999): " + e.getMessage());
        }

        System.out.println("Por nome: " + porNome());
        System.out.println("Primeira letra: " + primeiraLetra());
        System.out.println(formatada());
        System.out.println("lista primeira letra: "+listaPrimeiraLetra());
        System.out.println("lista primeira letra: "+primeirasLetrasJuntas());
    }
}
