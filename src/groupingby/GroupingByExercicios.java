package groupingby;

import modelo.Produto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Série completa de groupingBy: classificador + downstream.
 */
public class GroupingByExercicios {

    public static void main(String[] args) {
        List<Produto> produtos = Produto.amostra();

        // agrupar por estoque
        Map<Boolean, List<Produto>> porEstoque = produtos.stream()
                .collect(groupingBy(p -> p.getQuantidade() > 0));
        System.out.println("Por estoque: " + porEstoque);

        // contar por grupo (counting devolve Long)
        Map<Boolean, Long> qtd = produtos.stream()
                .collect(groupingBy(p -> p.getQuantidade() > 0, Collectors.counting()));
        System.out.println("Quantidade: " + qtd);

        // somar preços por grupo
        Map<Boolean, Double> soma = produtos.stream()
                .collect(groupingBy(p -> p.getQuantidade() > 0,
                        Collectors.summingDouble(Produto::getPreco)));
        System.out.println("Soma: " + soma);

        // só os nomes — mapping dentro, o classificador ainda recebe o Produto
        Map<Boolean, List<String>> nomes = produtos.stream()
                .collect(groupingBy(p -> p.getQuantidade() > 0,
                        Collectors.mapping(Produto::getNome, Collectors.toList())));
        System.out.println("Nomes: " + nomes);

        // o mais caro de cada faixa
        Map<String, Optional<Produto>> maisCaro = produtos.stream()
                .collect(groupingBy(
                        p -> p.getPreco() > 1000 ? "caro" : "acessível",
                        Collectors.maxBy(Comparator.comparingDouble(Produto::getPreco))));
        System.out.println("Mais caro por faixa: " + maisCaro);

        // faixas múltiplas — do maior para o menor na cadeia de if
        Map<String, Long> porFaixa = produtos.stream()
                .collect(groupingBy(p -> {
                    if (p.getPreco() > 1000) return "muito caro";
                    if (p.getPreco() > 700) return "caro";
                    if (p.getPreco() > 300) return "moderado";
                    return "barato";
                }, Collectors.counting()));
        System.out.println("Por faixa: " + porFaixa);
    }
}
