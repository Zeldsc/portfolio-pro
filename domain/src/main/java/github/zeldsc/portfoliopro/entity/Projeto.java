package github.zeldsc.portfoliopro.entity;

import github.zeldsc.portfoliopro.enums.Risco;
import github.zeldsc.portfoliopro.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Projeto {

    private final Long id;
    private final String nome;
    private final LocalDate dataInicio;
    private final LocalDate dataPrevisaoFim;
    private final LocalDate dataFim;
    private final String descricao;
    private final Status status;
    private final BigDecimal orcamento;
    private final Risco risco;
    private final Pessoa gerente;
    private final List<Pessoa> membros;

    private Projeto(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataInicio = builder.dataInicio;
        this.dataPrevisaoFim = builder.dataPrevisaoFim;
        this.dataFim = builder.dataFim;
        this.descricao = builder.descricao;
        this.status = builder.status;
        this.orcamento = builder.orcamento;
        this.risco = builder.risco;
        this.gerente = builder.gerente;
        this.membros = builder.membros;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataPrevisaoFim() {
        return dataPrevisaoFim;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public Pessoa getGerente() {
        return gerente;
    }

    public Risco getRisco() {
        return risco;
    }

    public List<Pessoa> getMembros() {
        return membros;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String nome;
        private LocalDate dataInicio;
        private LocalDate dataPrevisaoFim;
        private LocalDate dataFim;
        private String descricao;
        private Status status;
        private BigDecimal orcamento;
        private Risco risco;
        private Pessoa gerente;
        private List<Pessoa> membros;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder dataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; return this; }
        public Builder dataPrevisaoFim(LocalDate dataPrevisaoFim) { this.dataPrevisaoFim = dataPrevisaoFim; return this; }
        public Builder dataFim(LocalDate dataFim) { this.dataFim = dataFim; return this; }
        public Builder descricao(String descricao) { this.descricao = descricao; return this; }
        public Builder status(Status status) { this.status = status; return this; }
        public Builder orcamento(BigDecimal orcamento) { this.orcamento = orcamento; return this; }
        public Builder risco(Risco risco) { this.risco = risco; return this; }
        public Builder gerente(Pessoa gerente) { this.gerente = gerente; return this; }
        public Builder membros(List<Pessoa> membros) { this.membros = membros; return this; }
        public Projeto build() { return new Projeto(this); }
    }
}