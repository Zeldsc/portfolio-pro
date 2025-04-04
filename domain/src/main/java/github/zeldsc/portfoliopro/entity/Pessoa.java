package github.zeldsc.portfoliopro.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pessoa {

    private Long id;
    private final String nome;
    private final LocalDate dataNascimento;
    private final String cpf;
    private final boolean funcionario;
    private final boolean gerente;
    private final List<Projeto> projetos;

    private Pessoa(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.dataNascimento = builder.dataNascimento;
        this.cpf = builder.cpf;
        this.funcionario = builder.funcionario;
        this.gerente = builder.gerente;
        this.projetos = builder.projetos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isFuncionario() {
        return funcionario;
    }

    public boolean isGerente() {
        return gerente;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public Pessoa comId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private String nome;
        private LocalDate dataNascimento;
        private String cpf;
        private boolean funcionario;
        private boolean gerente;
        private List<Projeto> projetos = new ArrayList<>();

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder dataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder funcionario(boolean funcionario) {
            this.funcionario = funcionario;
            return this;
        }

        public Builder gerente(boolean gerente) {
            this.gerente = gerente;
            return this;
        }

        public Builder projetos(List<Projeto> projetos) {
            this.projetos = projetos;
            return this;
        }

        public Pessoa build() {
            return new Pessoa(this);
        }
    }
}
