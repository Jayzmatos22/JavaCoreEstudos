package modelo;

import java.util.List;

public class Produto {

    private final String nome;
    private final double preco;
    private final int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + preco + ", qtd " + quantidade + ")";
    }

    // Massa de dados compartilhada pelos exercícios.
    // "mouse" minúsculo de propósito (exercício 1 de sorted);
    // Pendrive e Cabo HDMI empatam no menor preço (exercício dos empatados).
    public static List<Produto> amostra() {
        return List.of(
                new Produto("Notebook", 3500.0, 5),
                new Produto("mouse", 80.0, 0),
                new Produto("Teclado", 150.0, 20),
                new Produto("Monitor", 900.0, 0),
                new Produto("Headset", 150.0, 12),
                new Produto("Cadeira", 1200.0, 3),
                new Produto("Webcam", 350.0, 8),
                new Produto("Pendrive", 45.0, 30),
                new Produto("Cabo HDMI", 45.0, 0)
        );
    }
}
