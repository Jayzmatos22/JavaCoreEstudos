package streams;

import modelo.Produto;

import java.util.Comparator;
import java.util.Arrays;


public class ProdutoMaisCaro {

    public static void main(String[] args) {
        Produto[] produtos = Produto.amostra().toArray(new Produto[0]);

        String caro = Arrays.stream(produtos)
                .filter(q -> q.getPreco() > 0)
                .max(Comparator.comparing(Produto::getPreco))
                .map(Produto::getNome)
                .orElse("nenhum produto encontrado");

        System.out.println("Mais caro: " + caro);
    }


}
