package excecoes;

import modelo.Produto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Busca que lança, exceção com causa, try-with-resources e fail fast.
 */
public class ExcecoesExercicios {

    public static Produto buscarPorNome(String nomeP, List<Produto> lista) {
        return lista.stream()
                .filter(prod -> prod.getNome().equalsIgnoreCase(nomeP))
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException(nomeP));
    }

    public static String lerLinha(String caminho) {
        try (var reader = Files.newBufferedReader(Path.of(caminho))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new ArquivoNaoEncontrado("Erro ao ler " + caminho, e);
        }
    }

    public static double dividir(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("divisor não pode ser zero");
        return (double) a / b;
    }

    public static void main(String[] args) {
        List<Produto> produtos = Produto.amostra();

        System.out.println("Busca: " + buscarPorNome("teclado", produtos));
        try {
            buscarPorNome("Impressora", produtos);
        } catch (ProdutoNaoEncontradoException e) {
            System.out.println("Busca falhou: " + e.getMessage());
        }

        try {
            lerLinha("arquivo-que-nao-existe.txt");
        } catch (ArquivoNaoEncontrado e) {
            System.out.println(e);
            System.out.println("Caused by: " + e.getCause());   // o ganho do encadeamento
        }

        System.out.println("10 / 4 = " + dividir(10, 4));
        try {
            dividir(5, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Fail fast: " + e.getMessage());
        }
    }
}
