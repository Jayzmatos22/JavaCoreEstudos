package modelo;

import java.util.List;

public class Usuario {

    private String nome;
    private String email;
    private Long saldo;
    private BancoEnum banco;

    public Usuario(String nome, String email, Long saldoInicial, BancoEnum banco) {
        this.nome = nome;
        this.email = email;
        this.banco = banco;

        if (saldoInicial >= 0) {
            this.saldo = saldoInicial;
        } else {
            this.saldo = 0L;
        }
    }

    public void depositar(Long valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado no " + banco.getNome() + ".");
        } else {
            System.out.println("O valor do depósito deve ser maior que zero.");
        }
    }

    public boolean sacar(Long valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado no " + banco.getNome() + ".");
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
            return false;
        }
    }

    public Long getSaldo() {
        return this.saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BancoEnum getBanco() {
        return banco;
    }

    public void setBanco(BancoEnum banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + saldo + ")";
    }

    // Massa de dados compartilhada pelos exercícios com usuários.
    public static List<Usuario> amostra() {
        return List.of(
                // Saldos abaixo de 300
                new Usuario("Ana Silva", "ana.silva@email.com", 150L, BancoEnum.ITAU),
                new Usuario("Lucas Fernandes", "lucas.fernandes@email.com", 241L, BancoEnum.ITAU),
                new Usuario("Patrícia Souza", "patricia.souza@email.com", 90L, BancoEnum.CAIXA),
                new Usuario("Wagner Santos", "wagner.santos@email.com", 210L, BancoEnum.SICOOB),

                // Saldos entre 300 e 1500
                new Usuario("Hugo Martins", "hugo.martins@email.com", 310L, BancoEnum.BTG_PACTUAL),
                new Usuario("Isabela Ribeiro", "isabela.ribeiro@email.com", 600L, BancoEnum.SICREDI),
                new Usuario("Felipe Mendes", "felipe.mendes@email.com", 1200L, BancoEnum.CAIXA),
                new Usuario("Mariana Costa", "mariana.costa@email.com", 1450L, BancoEnum.BRADESCO),
                new Usuario("Rafael Lima", "rafael.lima@email.com", 850L, BancoEnum.INTER),
                new Usuario("Thiago Martins", "thiago.martins@email.com", 500L, BancoEnum.SICREDI),
                new Usuario("Yasmin Cardoso", "yasmin.cardoso@email.com", 1100L, BancoEnum.ITAU),

                // Saldos acima de 1500
                new Usuario("Carlos Souza", "carlos.souza@email.com", 32050L, BancoEnum.ITAU),
                new Usuario("Beatriz Lima", "beatriz.lima@email.com", 205000L, BancoEnum.BRADESCO),
                new Usuario("Daniel Costa", "daniel.costa@email.com", 8000L, BancoEnum.BANCO_DO_BRASIL),
                new Usuario("Elena Rocha", "elena.rocha@email.com", 4030L, BancoEnum.SANTANDER),
                new Usuario("Gabriela Dias", "gabriela.dias@email.com", 9500L, BancoEnum.INTER),
                new Usuario("João Pedro", "joao.pedro@email.com", 120500L, BancoEnum.SICOOB),
                new Usuario("Nicolas Almeida", "nicolas.almeida@email.com", 45000L, BancoEnum.SANTANDER),
                new Usuario("Sabrina Ramos", "sabrina.ramos@email.com", 3200L, BancoEnum.BANCO_DO_BRASIL),
                new Usuario("Vanessa Dias", "vanessa.dias@email.com", 185000L, BancoEnum.BTG_PACTUAL)
        );
    }
}
