package comparator;

public class Paciente {

    private final String nome;
    private final int prioridade;
    private final int idade;
    private final int tempoEsperaMinutos;

    public Paciente(String nome, int prioridade, int idade, int tempoEsperaMinutos) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.idade = idade;
        this.tempoEsperaMinutos = tempoEsperaMinutos;
    }

    public String getNome() {
        return nome;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public int getIdade() {
        return idade;
    }

    public int getTempoEsperaMinutos() {
        return tempoEsperaMinutos;
    }

    @Override
    public String toString() {
        return nome;
    }
}
