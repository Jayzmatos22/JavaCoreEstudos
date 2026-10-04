package equalshashcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Demonstração do contrato equals/hashCode quebrado de propósito:
 * equals por nome, hashCode por preço. Nenhum erro, só um get que devolve null.
 */
public class ContratoQuebrado {

    static class Produto {
        private final String nome;
        private final double preco;
        private final int quantidade;

        Produto(String nome, double preco, int quantidade) {
            this.nome = nome;
            this.preco = preco;
            this.quantidade = quantidade;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Produto outro)) return false;
            return Objects.equals(nome, outro.nome);
        }

        @Override
        public int hashCode() {
            return Objects.hash(preco);   // ERRADO de propósito: campo diferente do equals
        }
    }

    public static void main(String[] args) {
        Produto x = new Produto("Mouse", 150.0, 50);
        Produto y = new Produto("Mouse", 200.0, 30);

        Map<Produto, String> m = new HashMap<>();
        m.put(x, "guardado");

        System.out.println("x.equals(y): " + x.equals(y));                         // true
        System.out.println("hashCode igual: " + (x.hashCode() == y.hashCode()));    // false
        System.out.println("m.get(y): " + m.get(y));                                // null, sem erro
    }
}
