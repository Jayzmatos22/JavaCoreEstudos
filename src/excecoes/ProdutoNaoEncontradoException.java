package excecoes;

public class ProdutoNaoEncontradoException extends RuntimeException {

    public ProdutoNaoEncontradoException(String nome) {
        super("Produto não encontrado: " + nome);
    }
}
