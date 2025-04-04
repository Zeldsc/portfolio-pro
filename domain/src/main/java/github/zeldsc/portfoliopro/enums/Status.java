package github.zeldsc.portfoliopro.enums;

public enum Status {
    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
