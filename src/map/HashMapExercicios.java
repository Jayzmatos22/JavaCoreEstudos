package map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Série completa de HashMap: métodos de conveniência, merge e stream sobre entrySet.
 */
public class HashMapExercicios {

    public static void main(String[] args) {
        Map<String, Integer> estoque = new HashMap<>();
        estoque.put("mouse", 15);
        estoque.put("teclado", 25);
        estoque.put("monitor", 0);
        estoque.put("headset", 0);
        estoque.put("notebook", 7);

        System.out.println("get(webcam): " + estoque.get("webcam"));                        // null
        System.out.println("getOrDefault(webcam): " + estoque.getOrDefault("webcam", 0));   // 0

        estoque.putIfAbsent("monitor", 10);        // já existe (com 0), não grava
        estoque.putIfAbsent("cadeira", 4);         // ausente, grava
        System.out.println("Após putIfAbsent: " + estoque);

        estoque.values().removeIf(v -> v == 0);    // view: altera o mapa
        System.out.println("Sem zerados: " + estoque);

        estoque.replaceAll((k, v) -> v * 2);
        System.out.println("Dobrado: " + estoque);

        estoque.merge("mouse", 5, Integer::sum);   // existe: soma
        estoque.merge("webcam", 5, Integer::sum);  // ausente: grava 5

        estoque.forEach((nome, qtd) -> System.out.println(nome + ": " + qtd));

        List<String> mais20 = estoque.entrySet().stream()
                .filter(e -> e.getValue() > 20)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Mais de 20: " + mais20);

        Map<Integer, String> reverso = estoque.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        System.out.println("Reverso: " + reverso);

        // explode se dois produtos tiverem a mesma quantidade (Duplicate key)
        estoque.put("pendrive", estoque.get("webcam"));
        try {
            estoque.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        } catch (IllegalStateException e) {
            System.out.println("Com quantidade repetida: " + e.getMessage());
        }
    }
}
