package streams;

import modelo.Produto;

import java.util.List;

/**
 * Todos os produtos empatados no preço mínimo.
 * Duas passadas O(n) ganham de uma stream só com groupingBy + TreeMap (O(n log n)).
 */
public class EmpatadosNoMenorPreco {

    public static void main(String[] args) {
        List<Produto> produtos = Produto.amostra();

        double menor = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .min()
                .orElseThrow();

        List<Produto> baratos = produtos.stream()
                .filter(p -> p.getPreco() == menor)
                .toList();

        System.out.println("Menor preço: " + menor);
        System.out.println("Empatados: " + baratos);
    }
}
