package streams;

import modelo.Produto;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Produto mais barato que tem estoque.
 * Depois do min() o encadeamento continua, mas na API do Optional.
 */
public class ProdutoMaisBarato {

    public static void main(String[] args) {
        Produto[] produtos = Produto.amostra().toArray(new Produto[0]);

        String nome = Arrays.stream(produtos)                       // Stream<Produto>
                .filter(p -> p.getQuantidade() > 0)                 // Stream<Produto>
                .min(Comparator.comparingDouble(Produto::getPreco)) // Optional<Produto>
                .map(Produto::getNome)                              // Optional<String> (Optional.map)
                .orElse("nenhum");                                  // String


        System.out.println("Mais barato com estoque: " + nome);
    }
}
