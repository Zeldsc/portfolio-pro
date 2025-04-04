package github.zeldsc.portfoliopro.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException(Long id) {
        super("Projeto com ID " + id + " não encontrado.");
    }
}