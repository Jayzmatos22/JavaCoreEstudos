package excecoes;

public class ArquivoNaoEncontrado extends RuntimeException {

    public ArquivoNaoEncontrado(String msg) {
        super(msg);
    }

    // Guarda a causa: o stack trace mostra o "Caused by:" com o motivo real
    public ArquivoNaoEncontrado(String msg, Throwable causa) {
        super(msg, causa);
    }
}
