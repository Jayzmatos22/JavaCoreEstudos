package sorted;

import modelo.Produto;

import java.util.Comparator;
import java.util.List;

/**
 * Série completa de sorted. A lista original nunca é alterada.
 */
public class SortedExercicios {

    public static void main(String[] args) {
        List<Produto> produtos = Produto.amostra();

        // 1. alfabética — "mouse" minúsculo vai para o fim (A-Z é 65-90, a-z é 97-122)
        imprimir("1. Alfabética", produtos.stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .toList());

        // 2. ignorando maiúsculas
        imprimir("2. Case insensitive", produtos.stream()
                .sorted(Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER))
                .toList());

        // 3. mais caro ao mais barato
        imprimir("3. Preço ↓", produtos.stream()
                .sorted(Comparator.comparingDouble(Produto::getPreco).reversed())
                .toList());

        // 4. preço ↑, desempate por nome
        imprimir("4. Preço ↑, nome", produtos.stream()
                .sorted(Comparator.comparingDouble(Produto::getPreco)
                        .thenComparing(Produto::getNome))
                .toList());

        // 5. quantidade ↓, desempate por preço ↑
        imprimir("5. Quantidade ↓, preço ↑", produtos.stream()
                .sorted(Comparator.comparingInt(Produto::getQuantidade).reversed()
                        .thenComparingDouble(Produto::getPreco))
                .toList());

        // 6. 3 mais baratos com estoque, só os nomes
        List<String> tresMaisBaratos = produtos.stream()
                .filter(p -> p.getQuantidade() > 0)
                .sorted(Comparator.comparingDouble(Produto::getPreco))
                .limit(3)
                .map(Produto::getNome)
                .toList();
        System.out.println("6. 3 mais baratos com estoque: " + tresMaisBaratos);
    }

    private static void imprimir(String titulo, List<Produto> lista) {
        System.out.println(titulo + ":");
        lista.forEach(p -> System.out.println("   " + p));
    }
}
