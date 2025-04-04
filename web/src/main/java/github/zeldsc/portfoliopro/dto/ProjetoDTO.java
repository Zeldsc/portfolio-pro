package github.zeldsc.portfoliopro.dto;


import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Projeto;
import github.zeldsc.portfoliopro.enums.Risco;
import github.zeldsc.portfoliopro.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDTO {

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
    private List<Pessoa> membros = new ArrayList<>();

    public Projeto toDomain() {
        return Projeto.builder()
                .id(id)
                .nome(nome)
                .dataInicio(dataInicio)
                .dataPrevisaoFim(dataPrevisaoFim)
                .dataFim(dataFim)
                .descricao(descricao)
                .status(status)
                .orcamento(orcamento)
                .risco(risco)
                .gerente(gerente)
                .membros(membros)
                .build();
    }

    public static ProjetoDTO fromDomain(Projeto projeto) {
        ProjetoDTO dto = new ProjetoDTO();
        dto.id = projeto.getId();
        dto.nome = projeto.getNome();
        dto.dataInicio = projeto.getDataInicio();
        dto.dataPrevisaoFim = projeto.getDataPrevisaoFim();
        dto.dataFim = projeto.getDataFim();
        dto.descricao = projeto.getDescricao();
        dto.status = projeto.getStatus();
        dto.orcamento = projeto.getOrcamento();
        dto.risco = projeto.getRisco();
        dto.gerente = projeto.getGerente();
        dto.membros = projeto.getMembros();
        return dto;
    }

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

    public Pessoa getGerente() {
        return gerente;
    }

    public void setGerente(Pessoa gerente) {
        this.gerente = gerente;
    }

    public List<Pessoa> getMembros() {
        return membros;
    }

    public void setMembros(List<Pessoa> membros) {
        this.membros = membros;
    }

    public String getDataInicioFormatada() {
        return dataInicio != null ? dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    public String getDataFimFormatada() {
        return dataFim != null ? dataFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    public String getDataPrevisaoFimFormatada() {
        return dataPrevisaoFim != null ? dataPrevisaoFim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }
} 
