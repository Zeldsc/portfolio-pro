package github.zeldsc.portfoliopro.enums;

public enum Risco {
    BAIXO("Baixo Risco"),
    MEDIO("Médio Risco"),
    ALTO("Alto Risco");

    private final String descricao;

    Risco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
