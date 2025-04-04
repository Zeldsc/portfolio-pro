package github.zeldsc.portfoliopro.database.entity;

import github.zeldsc.portfoliopro.enums.Risco;
import github.zeldsc.portfoliopro.enums.Status;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projeto")
public class ProjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(length = 5000)
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column
    private BigDecimal orcamento;

    @Enumerated(EnumType.ORDINAL)
    private Risco risco;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idgerente", nullable = false)
    private PessoaEntity gerente;

    @ManyToMany
    @JoinTable(
        name = "projeto_membros",
        joinColumns = @JoinColumn(name = "projeto_id"),
        inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private Set<PessoaEntity> membros = new HashSet<>();

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataPrevisaoFim() {
        return dataPrevisaoFim;
    }

    public void setDataPrevisaoFim(LocalDate dataPrevisaoFim) {
        this.dataPrevisaoFim = dataPrevisaoFim;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(BigDecimal orcamento) {
        this.orcamento = orcamento;
    }

    public Risco getRisco() {
        return risco;
    }

    public void setRisco(Risco risco) {
        this.risco = risco;
    }

    public PessoaEntity getGerente() {
        return gerente;
    }

    public void setGerente(PessoaEntity gerente) {
        this.gerente = gerente;
    }

    public Set<PessoaEntity> getMembros() {
        return membros;
    }

    public void setMembros(Set<PessoaEntity> membros) {
        this.membros = membros;
    }
}
