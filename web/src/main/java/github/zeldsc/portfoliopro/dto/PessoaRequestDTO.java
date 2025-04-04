package github.zeldsc.portfoliopro.dto;

import java.time.LocalDate;

public class PessoaRequestDTO {

    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private boolean funcionario;
    private boolean gerente;

    public PessoaRequestDTO() {
    }

    public PessoaRequestDTO(String nome, LocalDate dataNascimento, String cpf, boolean funcionario, boolean gerente) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.funcionario = funcionario;
        this.gerente = gerente;
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
}
