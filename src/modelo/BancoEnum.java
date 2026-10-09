package modelo;

public enum BancoEnum {
    BANCO_DO_BRASIL("001", "Banco do Brasil"),
    BRADESCO("237", "Bradesco"),
    ITAU("341", "Itaú Unibanco"),
    SANTANDER("033", "Santander"),
    CAIXA("104", "Caixa Econômica Federal"),
    NUBANK("260", "Nu Pagamentos (Nubank)"),
    INTER("077", "Banco Inter"),
    BTG_PACTUAL("208", "BTG Pactual"),
    SICREDI("748", "Sicredi"),
    SICOOB("756", "Sicoob");

    private final String codigo;
    private final String nome;

    BancoEnum(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
